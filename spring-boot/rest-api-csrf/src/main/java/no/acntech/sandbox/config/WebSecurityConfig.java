package no.acntech.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import no.acntech.sandbox.csrf.ImprovedCookieCsrfTokenRepository;

@SuppressWarnings("Duplicates")
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                .csrf(config -> config
                        .csrfTokenRepository(new ImprovedCookieCsrfTokenRepository())
                )
                .authorizeHttpRequests(config -> config
                        .anyRequest().permitAll()
                )
                .build();
    }
}
