package no.acntech.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig {

    @SuppressWarnings("Duplicates")
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                .csrf(config -> config
                        .requireCsrfProtectionMatcher(antMatcher("**/login"))
                        .disable()
                )
                .authorizeHttpRequests(config -> config
                        .requestMatchers(antMatcher("/**")).hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(config -> config
                        .loginPage("/login")
                        .defaultSuccessUrl("/").failureUrl("/login?error").permitAll()
                )
                .logout(config -> config
                        .logoutRequestMatcher(antMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .deleteCookies("JSESSIONID").invalidateHttpSession(true).permitAll()
                )
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring().requestMatchers(
                        antMatcher("/webjars/**"),
                        antMatcher("/assets/**"),
                        antMatcher("/h2-console/**"));
    }

  /*  @Bean
    protected AuthenticationManager authenticationManager(final AuthenticationManagerBuilder auth,
                                                          final AuthenticationProvider authenticationProvider) throws Exception {
        return auth
                .authenticationProvider(authenticationProvider)
                .build();
    }*/

    /*@Bean
    public AuthenticationProvider authenticationProvider(final UserDetailsService userDetailsService,
                                                         final PasswordEncoder passwordEncoder) {
        final var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
