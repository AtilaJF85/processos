package br.com.atilajf.processos.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "cors", ignoreUnknownFields = false)
public record CorsProperties(
    @NotBlank
    String allowedOriginPattern,

    @NotBlank
    String allowedMethods,

    @NotBlank
    String allowedHeaders,

    @NotNull
    boolean allowCredentials,

    @NotNull
    long maxAge
) {

}
