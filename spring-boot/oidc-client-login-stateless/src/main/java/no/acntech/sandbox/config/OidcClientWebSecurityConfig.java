package no.acntech.sandbox.config;

import no.acntech.sandbox.handler.OidcLogoutSuccessHandler;
import no.acntech.sandbox.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import no.acntech.sandbox.repository.InMemorySecurityContextRepository;
import no.acntech.sandbox.store.Store;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextRepository;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
public class OidcClientWebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http,
                                                   final SecurityContextRepository securityContextRepository,
                                                   final OidcLogoutSuccessHandler oidcLogoutSuccessHandler,
                                                   final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .securityContext().securityContextRepository(securityContextRepository)
                .and()
                .logout().clearAuthentication(true).logoutSuccessHandler(oidcLogoutSuccessHandler)
                .and()
                .oauth2Login().authorizationEndpoint().authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository)
                .and()
                .and()
                .build();
    }

    @Bean
    public SecurityContextRepository securityContextRepository(final Store<String, SecurityContext> securityContextStore) {
        return new InMemorySecurityContextRepository(securityContextStore);
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public OidcLogoutSuccessHandler oidcLogoutSuccessHandler(final ClientRegistrationRepository clientRegistrationRepository,
                                                             final Store<String, SecurityContext> securityContextStore) {
        var logoutSuccessHandler = new OidcLogoutSuccessHandler(clientRegistrationRepository, securityContextStore);
        logoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/");
        return logoutSuccessHandler;
    }
}
