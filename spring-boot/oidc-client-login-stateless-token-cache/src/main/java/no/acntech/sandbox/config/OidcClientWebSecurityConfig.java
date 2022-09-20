package no.acntech.sandbox.config;

import no.acntech.sandbox.handler.OidcLogoutSuccessHandler;
import no.acntech.sandbox.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import no.acntech.sandbox.resolver.CookieResolver;
import no.acntech.sandbox.service.RedisOAuth2AuthorizedClientService;
import no.acntech.sandbox.store.Store;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientId;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
public class OidcClientWebSecurityConfig {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final Store<OAuth2AuthorizedClientId, OAuth2AuthorizedClient> oAuth2AuthorizedClientStore;

    public OidcClientWebSecurityConfig(final ClientRegistrationRepository clientRegistrationRepository,
                                       final Store<OAuth2AuthorizedClientId, OAuth2AuthorizedClient> oAuth2AuthorizedClientStore) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.oAuth2AuthorizedClientStore = oAuth2AuthorizedClientStore;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .logout().clearAuthentication(true).logoutSuccessHandler(oidcLogoutSuccessHandler())
                .and()
                .oauth2Login().authorizationEndpoint().authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository())
                .and()
                .and()
                .build();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository(authorizationRequestCookieResolver());
    }

    @Bean
    public OAuth2AuthorizedClientService oAuth2AuthorizedClientService() {
        return new RedisOAuth2AuthorizedClientService(clientRegistrationRepository, oAuth2AuthorizedClientStore);
    }

    @Bean
    public OidcLogoutSuccessHandler oidcLogoutSuccessHandler() {
        var logoutSuccessHandler = new OidcLogoutSuccessHandler(clientRegistrationRepository, oAuth2AuthorizedClientStore);
        logoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/");
        return logoutSuccessHandler;
    }

    @Bean
    public CookieResolver authorizationRequestCookieResolver() {
        return CookieResolver.authorizationRequestCookieResolver();
    }

    @Bean
    public CookieResolver sessionCookieResolver() {
        return CookieResolver.sessionCookieResolver();
    }
}
