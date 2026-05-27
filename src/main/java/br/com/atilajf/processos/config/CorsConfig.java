package br.com.atilajf.processos.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class CorsConfig {

    @Bean
    CorsFilter corsConfigurationSource(CorsProperties corsProperties) {
        final var configuration = new CorsConfiguration();

        configuration.setMaxAge(corsProperties.maxAge());
        configuration.setAllowCredentials(corsProperties.allowCredentials());
        configuration.addAllowedOriginPattern(corsProperties.allowedOriginPattern());
        configuration.setAllowedMethods(Arrays.asList(corsProperties.allowedMethods().split(",")));
        configuration.setAllowedHeaders(Arrays.asList(corsProperties.allowedHeaders().split(",")));

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

}
