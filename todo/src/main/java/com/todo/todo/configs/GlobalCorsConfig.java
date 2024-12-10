/**
 * @author : tadiewa
 * date: 12/5/2024
 */


package com.todo.todo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); // Enable credentials if needed
        config.addAllowedOrigin("http://localhost:3000"); // Frontend origin
        config.addAllowedHeader("*"); // Allow all headers
        config.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, etc.)
        config.addExposedHeader("Authorization"); // Optional: Add exposed headers if needed

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
