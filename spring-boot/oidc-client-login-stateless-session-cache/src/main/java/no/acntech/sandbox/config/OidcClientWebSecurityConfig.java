package no.acntech.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.context.SecurityContextRepository;

import no.acntech.sandbox.handler.OidcLogoutSuccessHandler;
import no.acntech.sandbox.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import no.acntech.sandbox.repository.RedisSecurityContextRepository;
import no.acntech.sandbox.store.SecurityContextStore;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration(proxyBeanMethods = false)
public class OidcClientWebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http,
                                                   final SecurityContextRepository securityContextRepository,
                                                   final LogoutSuccessHandler logoutSuccessHandler,
                                                   final AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository) throws Exception {
        return http
                .sessionManagement(config -> config
                        .sessionCreationPolicy(STATELESS)
                )
                .authorizeHttpRequests(config -> config
                        .anyRequest()
                        .authenticated()
                )
                .securityContext(config -> config
                        .securityContextRepository(securityContextRepository)
                )
                .logout(config -> config
                        .clearAuthentication(true)
                        .logoutSuccessHandler(logoutSuccessHandler)
                )
                .oauth2Login(config -> config
                        .authorizationEndpoint(endpoint -> endpoint
                                .authorizationRequestRepository(authorizationRequestRepository)
                        )
                )
                .build();
    }

    @Bean
    public SecurityContextRepository securityContextRepository(final SecurityContextStore securityContextStore) {
        return new RedisSecurityContextRepository(securityContextStore);
    }

    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(final ClientRegistrationRepository clientRegistrationRepository,
                                                     final SecurityContextStore securityContextStore) {
        var logoutSuccessHandler = new OidcLogoutSuccessHandler(clientRegistrationRepository, securityContextStore);
        logoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/");
        return logoutSuccessHandler;
    }
}
