package no.acntech.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.savedrequest.RequestCache;

@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http,
                                                   final RequestCache requestCache) throws Exception {
        return http
                .authorizeHttpRequests(config -> config
                        .anyRequest()
                        .authenticated()
                )
                .oauth2Login(Customizer.withDefaults())
                .requestCache(config -> config
                        .requestCache(requestCache)
                )
                .exceptionHandling(config -> config
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .build();
    }
}
