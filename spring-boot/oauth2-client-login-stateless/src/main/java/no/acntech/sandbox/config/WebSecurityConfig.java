package no.acntech.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextRepository;

import no.acntech.sandbox.handler.OidcLogoutSuccessHandler;
import no.acntech.sandbox.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import no.acntech.sandbox.repository.InMemorySecurityContextRepository;
import no.acntech.sandbox.store.SecurityContextStore;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http,
                                                   final SecurityContextRepository securityContextRepository,
                                                   final OidcLogoutSuccessHandler oidcLogoutSuccessHandler,
                                                   final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) throws Exception {
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
                        .logoutSuccessHandler(oidcLogoutSuccessHandler)
                )
                .oauth2Login(config -> config
                        .authorizationEndpoint(endpoint -> endpoint
                                .authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository)
                        )
                )
                .build();
    }

    @Bean
    public SecurityContextRepository securityContextRepository(final SecurityContextStore securityContextStore) {
        return new InMemorySecurityContextRepository(securityContextStore);
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public OidcLogoutSuccessHandler oidcLogoutSuccessHandler(final ClientRegistrationRepository clientRegistrationRepository,
                                                             final SecurityContextStore securityContextStore) {
        var logoutSuccessHandler = new OidcLogoutSuccessHandler(clientRegistrationRepository, securityContextStore);
        logoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/");
        return logoutSuccessHandler;
    }
}
