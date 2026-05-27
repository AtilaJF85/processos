package br.com.atilajf.processos.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jspecify.annotations.NonNull;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.security.oauth2.client.autoconfigure.ConditionalOnOAuth2ClientRegistrationProperties;
import org.springframework.boot.security.oauth2.client.autoconfigure.OAuth2ClientProperties;
import org.springframework.boot.security.oauth2.server.resource.autoconfigure.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.SupplierClientRegistrationRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.net.URI;
import java.util.Objects;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtAuthConverterProperties.class)
public class JwtAuthConfig {

    private final OAuth2ClientProperties oAuth2ClientProperties;

    private OAuth2ClientProperties.Registration getKeycloak() {
        return oAuth2ClientProperties.getRegistration().get("keycloak");
    }

    private String getClientId() {
        return getKeycloak().getClientId();
    }

    private String getClientSecret() {
        return getKeycloak().getClientSecret();
    }

    @Bean
    public JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter() {
        return new JwtGrantedAuthoritiesConverter();
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpSecurityInfo httpSecurityInfo() {
        return new HttpSecurityInfo();
    }

    @Bean
    OAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clients) {
        final var service = new InMemoryOAuth2AuthorizedClientService(clients);
        final var manager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(clients, service);
        final var authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();
        manager.setAuthorizedClientProvider(authorizedClientProvider);
        return manager;
    }

    @Bean
    @ConditionalOnOAuth2ClientRegistrationProperties
    ClientRegistration authorizedClient(ClientRegistrationRepository clients) {
        if (clients instanceof InMemoryClientRegistrationRepository inMemoryClientRegistrationRepository) {
            return inMemoryClientRegistrationRepository.iterator().next();
        }
        if (clients instanceof SupplierClientRegistrationRepository supplierClientRegistrationRepository) {
            supplierClientRegistrationRepository.iterator().next();
        }
        return clients.findByRegistrationId("keycloak");
    }


    public static String getRealm(@NonNull OAuth2ResourceServerProperties oAuth2ResourceServerProperties) {
        final var issuerUri = URI.create(Objects.requireNonNull(oAuth2ResourceServerProperties.getJwt().getIssuerUri(), "Configuração do Issuer não encontrada!"));
        final var path = issuerUri.getPath();

        return path.replace("/auth", StringUtils.EMPTY)
            .replace("/realms/", StringUtils.EMPTY);
    }


    @Bean
    @ConditionalOnOAuth2ClientRegistrationProperties
    Keycloak keycloak(OAuth2ResourceServerProperties oAuth2ResourceServerProperties) {

        final var issuerUri = URI.create(oAuth2ResourceServerProperties.getJwt().getIssuerUri());
        final var path = issuerUri.getPath();

        if (
            "true".equalsIgnoreCase(System.getenv("FORCE_KEYCLOAK")) ||
            (StringUtils.isNotEmpty(path) && (path.toLowerCase().startsWith("/realms") || path.toLowerCase().startsWith("/auth/realms")))
        ) {

            final var port = (-1 == issuerUri.getPort()) ?
                (issuerUri.getScheme().equals("http") ? 80 : 443) :
                issuerUri.getPort();

            final var serverUrl = String.format(
                "%s://%s:%d%s",
                StringUtils.firstNonEmpty(issuerUri.getScheme(), "https"),
                issuerUri.getHost(),
                port,
                path.toLowerCase().contains("/auth") ? "/auth" : StringUtils.EMPTY
            );

            return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(getRealm(oAuth2ResourceServerProperties))
                .clientId(getClientId())
                .clientSecret(getClientSecret())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();

        }else {
            log.warn("O IdP configurado não é Keycloak.");
            return null;
        }
    }

}
