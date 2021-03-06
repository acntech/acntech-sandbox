package no.acntech.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.savedrequest.NullRequestCache;

import no.acntech.sandbox.cache.HttpCookieRequestCache;
import no.acntech.sandbox.handler.OidcLogoutSuccessHandler;
import no.acntech.sandbox.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import no.acntech.sandbox.repository.HttpCookieSecurityContextRepository;
import no.acntech.sandbox.store.InMemorySecurityContextStore;

import static no.acntech.sandbox.repository.InMemorySecurityContextRepository.SESSION_COOKIE_NAME;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
public class OidcClientWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientService authorizedClientService;

    public OidcClientWebSecurityConfig(final ClientRegistrationRepository clientRegistrationRepository,
                                       final OAuth2AuthorizedClientService authorizedClientService) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.authorizedClientService = authorizedClientService;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .requestCache().requestCache(new NullRequestCache())
                .and()
                .securityContext().securityContextRepository(httpCookieSecurityContextRepository())
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .logout().clearAuthentication(true).logoutSuccessHandler(oidcLogoutSuccessHandler())
                .and()
                .oauth2Login().authorizationEndpoint().authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository());
    }

    @Override
    public void configure(final WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/webjars/**", "/resources/**");
    }

    @Bean
    public HttpCookieSecurityContextRepository httpCookieSecurityContextRepository() {
        return new HttpCookieSecurityContextRepository(authorizedClientService);
    }

    @Bean
    public InMemorySecurityContextStore inMemorySecurityContextStore() {
        return new InMemorySecurityContextStore();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public HttpCookieRequestCache httpCookieRequestCache() {
        return new HttpCookieRequestCache();
    }

    @Bean
    public OidcLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcLogoutSuccessHandler logoutSuccessHandler = new OidcLogoutSuccessHandler(clientRegistrationRepository, inMemorySecurityContextStore());
        logoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/");
        logoutSuccessHandler.setDeleteCookies(SESSION_COOKIE_NAME);
        return logoutSuccessHandler;
    }
}
