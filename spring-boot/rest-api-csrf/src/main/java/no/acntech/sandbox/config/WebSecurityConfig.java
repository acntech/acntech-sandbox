package no.acntech.sandbox.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import no.acntech.sandbox.csrf.ImprovedCookieCsrfTokenRepository;

@SuppressWarnings("Duplicates")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().csrfTokenRepository(new ImprovedCookieCsrfTokenRepository())
                .and()
                .authorizeRequests()
                .anyRequest().permitAll();
    }
}
