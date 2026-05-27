package br.com.atilajf.processos.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Info info(@Value("${app.name}") String appName, @Value("${app.version}") String appVersion) {
        return new Info()
            .title(StringUtils.firstNonEmpty(appName, "getInsight.it"))
            .description("Feito com Arquitetura Spring Boot da <a href='https://getinsight.it'>getInsight.it</a>")
            .contact(new Contact().name("Contato").email("contato@getinsight.it"))
            .version(appVersion);

    }

    @Bean
    @ConditionalOnMissingBean
    public Scopes scopes() {
        return new Scopes();
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityScheme securityScheme(
        Scopes scopes,

        @Value("${springdoc.swagger-ui.oauth.authorization-url}")
        String authUrl,

        @Value("${springdoc.swagger-ui.oauth.token-url}")
        String tokenUrl
    ) {

        final var flow = new OAuthFlow()
            .authorizationUrl(authUrl)
            .tokenUrl(tokenUrl)
            .refreshUrl(tokenUrl)
            .scopes(scopes);

        return new SecurityScheme()
            .type(SecurityScheme.Type.OAUTH2)
            .name("OAuth 2.0 - OIDC")
            .description("OAuth2 - OpenID Connect")
            .flows(new OAuthFlows()
                .authorizationCode(flow)
            )
            .in(SecurityScheme.In.COOKIE);
    }

    @Bean
    @ConditionalOnMissingBean
    public Components components(SecurityScheme securityScheme) {
        return new Components()
            .addSecuritySchemes(securityScheme.getName(), securityScheme);
    }

    @Bean
    @ConditionalOnMissingBean
    public OpenAPI customOpenAPI(Info info, Components components, SecurityScheme securityScheme) {
        return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList(securityScheme.getName(), "*"))
            .components(components)
            .info(info);
    }

}
