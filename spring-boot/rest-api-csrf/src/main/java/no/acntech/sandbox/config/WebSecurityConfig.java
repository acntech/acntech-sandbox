package no.acntech.sandbox.config;

import no.acntech.sandbox.csrf.ImprovedCookieCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("Duplicates")
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                .csrf().csrfTokenRepository(new ImprovedCookieCsrfTokenRepository())
                .and()
                .authorizeHttpRequests()
                .anyRequest().permitAll()
                .and()
                .build();
    }
}
