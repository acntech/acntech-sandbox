package no.acntech.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import no.acntech.sandbox.repository.InMemorySecurityContextRepository;

import static no.acntech.sandbox.repository.InMemorySecurityContextRepository.SESSION_COOKIE_NAME;

@SuppressWarnings("Duplicates")
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .securityContext().securityContextRepository(securityContextRepository())
                .and()
                .authorizeRequests()
                .antMatchers("/**").access("hasRole('USER')")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies(SESSION_COOKIE_NAME)
                .and()
                .csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login"));
    }

    @Override
    public void configure(final WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/webjars/**", "/resources/**");
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("USER", "ADMIN");
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new InMemorySecurityContextRepository();
    }
}
