package no.acntech.sandbox.config;

import no.acntech.sandbox.repository.InMemorySecurityContextRepository;
import no.acntech.sandbox.resolver.CookieResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("Duplicates")
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http,
                                                   final SecurityContextRepository securityContextRepository) throws Exception {
        return http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .securityContext().securityContextRepository(securityContextRepository)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies(CookieResolver.SESSION_COOKIE_NAME)
                .and()
                .csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login"))
                .and()
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring().requestMatchers("/webjars/**", "/resources/**");
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

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new InMemorySecurityContextRepository();
    }
}
