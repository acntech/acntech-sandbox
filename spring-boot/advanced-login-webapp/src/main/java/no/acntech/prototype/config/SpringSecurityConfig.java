package no.acntech.prototype.config;

import no.acntech.prototype.service.auth.AuthUserDetailsService;
import no.acntech.prototype.util.security.AuthPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity(debug = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthUserDetailsService authUserDetailsService;

    @Autowired
    public SpringSecurityConfig(final AuthUserDetailsService authUserDetailsService) {
        this.authUserDetailsService = authUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").access("hasRole('USER')")
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error").permitAll()
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).permitAll()
                .and().csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login"));
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/resources/**");
    }

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new AuthPasswordEncoder();
    }
}
