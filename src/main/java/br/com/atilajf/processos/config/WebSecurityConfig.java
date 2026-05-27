package br.com.atilajf.processos.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;
    private final HttpSecurityInfo httpSecurityInfo;

    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {


        http.authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.OPTIONS).permitAll());

        http.authorizeHttpRequests(requests -> requests
            .requestMatchers(httpSecurityInfo.getPermitAll())
            .permitAll());


        httpSecurityInfo.getAnyRequestAuthenticated().forEach((key, value) -> {
            try {
                http.authorizeHttpRequests(requests -> requests
                    .requestMatchers(value).hasRole(key));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated());

        http.oauth2ResourceServer(server -> server
            .jwt(jwt -> jwt
                .jwtAuthenticationConverter(jwtAuthConverter)));
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }

}
