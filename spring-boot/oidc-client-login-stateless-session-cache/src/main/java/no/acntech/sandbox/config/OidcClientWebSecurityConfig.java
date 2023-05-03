package no.acntech.sandbox.config;

import no.acntech.sandbox.handler.OidcLogoutSuccessHandler;
import no.acntech.sandbox.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import no.acntech.sandbox.repository.RedisSecurityContextRepository;
import no.acntech.sandbox.store.SecurityContextStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.context.SecurityContextRepository;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration(proxyBeanMethods = false)
public class OidcClientWebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http,
                                                   final SecurityContextRepository securityContextRepository,
                                                   final LogoutSuccessHandler logoutSuccessHandler,
                                                   final AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .securityContext().securityContextRepository(securityContextRepository)
                .and()
                .logout().clearAuthentication(true).logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .oauth2Login().authorizationEndpoint().authorizationRequestRepository(authorizationRequestRepository)
                .and()
                .and()
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
