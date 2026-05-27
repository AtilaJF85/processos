package br.com.atilajf.processos.config;

import java.util.Collections;
import java.util.Map;

public class HttpSecurityInfo {

    public String[] getPermitAll() {
        return new String[]{"/",
            "/h2/*",
            "/lib/*",
            "/css/*",
            "/fonts/*",
            "/images/*",
            "/public/**",
            "/o2c.html",
            "/index.html",
            "/webjars/**",
            "/api-docs/**",
            "/error",
            "/error/**",
            "/v3/api-docs/**",
            "/v3/api-docs",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/login.html",
            "/login",
            "/sso/logout",
            "/*.css",
            "/img/**",
            "/third-party/**",
            "/sso/login",
            "/actuator/**",
            "/actuator/info",
            "/actuator/refresh",
            "/actuator/bus-env",
            "/actuator/bus-refresh",
            "/actuator/bus-refresh/**",
            "/actuator/health",
            "/actuator/health/**",
            "/actuator/prometheus",
            "/actuator/prometheus/**" };
    }



    public Map<String, String[]> getAnyRequestAuthenticated() {
        //chave: role
        //valor: antMatchers
        return Collections.emptyMap();
    }

}
