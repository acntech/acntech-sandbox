package no.acntech.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.context.SecurityContextRepository;

import no.acntech.sandbox.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import no.acntech.sandbox.repository.InMemorySecurityContextRepository;

@EnableWebSecurity
public class OAuth2ClientWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .securityContext().securityContextRepository(securityContextRepository())
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login().authorizationEndpoint().authorizationRequestRepository(authorizationRequestRepository());
    }

    @Override
    public void configure(final WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/webjars/**", "/resources/**");
    }

    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new InMemorySecurityContextRepository();
    }
}
