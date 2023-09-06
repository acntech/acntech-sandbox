package no.acntech.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import no.acntech.sandbox.handler.OidcLogoutSuccessHandler;
import no.acntech.sandbox.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import no.acntech.sandbox.service.RedisOAuth2AuthorizedClientService;
import no.acntech.sandbox.store.OAuth2AuthorizedClientStore;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http,
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
    public HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public OAuth2AuthorizedClientService oAuth2AuthorizedClientService(final ClientRegistrationRepository clientRegistrationRepository,
                                                                       final OAuth2AuthorizedClientStore oAuth2AuthorizedClientStore) {
        return new RedisOAuth2AuthorizedClientService(clientRegistrationRepository, oAuth2AuthorizedClientStore);
    }

    @Bean
    public OidcLogoutSuccessHandler oidcLogoutSuccessHandler(final ClientRegistrationRepository clientRegistrationRepository,
                                                             final OAuth2AuthorizedClientStore oAuth2AuthorizedClientStore) {
        var logoutSuccessHandler = new OidcLogoutSuccessHandler(clientRegistrationRepository, oAuth2AuthorizedClientStore);
        logoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/");
        return logoutSuccessHandler;
    }
}
