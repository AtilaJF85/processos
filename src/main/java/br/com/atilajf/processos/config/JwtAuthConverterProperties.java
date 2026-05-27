package br.com.atilajf.processos.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "jwt.auth.converter", ignoreUnknownFields = false)
public record JwtAuthConverterProperties(
    @NotBlank
    String resourceId,

    @NotBlank
    String principalAttribute
) {

}
