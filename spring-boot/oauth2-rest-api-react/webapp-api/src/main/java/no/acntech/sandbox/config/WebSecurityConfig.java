package no.acntech.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.RequestCache;

import no.acntech.sandbox.error.UnAuthenticatedEntryPoint;

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
                        .authenticationEntryPoint(new UnAuthenticatedEntryPoint("acntech-generic-client"))
                )
                .build();
    }
}
