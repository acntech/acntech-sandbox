package no.acntech.sandbox.config;

import no.acntech.sandbox.handler.OidcLogoutSuccessHandler;
import no.acntech.sandbox.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import no.acntech.sandbox.repository.RedisSecurityContextRepository;
import no.acntech.sandbox.store.SecurityContextStore;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextRepository;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
public class OidcClientWebSecurityConfig {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final SecurityContextStore securityContextStore;

    public OidcClientWebSecurityConfig(final ClientRegistrationRepository clientRegistrationRepository,
                                       final SecurityContextStore securityContextStore) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.securityContextStore = securityContextStore;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .securityContext().securityContextRepository(inMemorySecurityContextRepository())
                .and()
                .logout().clearAuthentication(true).logoutSuccessHandler(oidcLogoutSuccessHandler())
                .and()
                .oauth2Login().authorizationEndpoint().authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository())
                .and()
                .and()
                .build();
    }

    @Bean
    public SecurityContextRepository inMemorySecurityContextRepository() {
        return new RedisSecurityContextRepository(securityContextStore);
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public OidcLogoutSuccessHandler oidcLogoutSuccessHandler() {
        var logoutSuccessHandler = new OidcLogoutSuccessHandler(clientRegistrationRepository, securityContextStore);
        logoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/");
        return logoutSuccessHandler;
    }
}
