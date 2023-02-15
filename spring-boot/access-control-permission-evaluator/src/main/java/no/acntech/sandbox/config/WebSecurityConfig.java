package no.acntech.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("Duplicates")
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        final var user = User.builder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        final var admin = User.builder()
                .username("admin")
                .password("admin")
                .roles("USER", "ADMIN")
                .build();
        final var anonymous = User.builder()
                .username("anonymous")
                .password("anonymous")
                .roles("ANONYMOUS")
                .build();
        return new InMemoryUserDetailsManager(user, admin, anonymous);
    }
}
