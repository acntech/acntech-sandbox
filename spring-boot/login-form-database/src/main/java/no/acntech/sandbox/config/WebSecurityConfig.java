package no.acntech.sandbox.config;

import no.acntech.sandbox.service.DefaultUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DefaultUserDetailsService userDetailsService;

    public WebSecurityConfig(final DefaultUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @SuppressWarnings("Duplicates")
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").access("hasRole('USER')")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/").failureUrl("/login?error").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID").invalidateHttpSession(true).permitAll()
                .and()
                .csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login"));
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/webjars/**", "/resources/**", "/h2-console/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
