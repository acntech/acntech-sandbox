package no.acntech.sandbox.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.context.SecurityContextRepository;

import no.acntech.sandbox.handler.OidcLogoutSuccessHandler;
import no.acntech.sandbox.repository.CookieAuthorizationRequestRepository;
import no.acntech.sandbox.repository.RedisSecurityContextRepository;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http,
                                                   final SecurityContextRepository securityContextRepository,
                                                   final LogoutSuccessHandler logoutSuccessHandler,
                                                   final CookieAuthorizationRequestRepository authorizationRequestRepository) throws Exception {
        return http
                .sessionManagement(config -> config
                        .sessionCreationPolicy(STATELESS)
                )
                .authorizeHttpRequests(config -> config
                        .anyRequest()
                        .authenticated()
                )
                .securityContext(config -> config
                        .securityContextRepository(securityContextRepository)
                )
                .logout(config -> config
                        .clearAuthentication(true)
                        .logoutSuccessHandler(logoutSuccessHandler)
                )
                .oauth2Login(config -> config
                        .authorizationEndpoint(endpoint -> endpoint
                                .authorizationRequestRepository(authorizationRequestRepository)
                        )
                )
                .build();
    }

    @Bean
    public RedisSecurityContextRepository redisSecurityContextRepository(
            @Qualifier("securityContextRedisTemplate") final RedisTemplate<String, SecurityContext> redisTemplate) {
        return new RedisSecurityContextRepository(redisTemplate);
    }

    @Bean
    public CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new CookieAuthorizationRequestRepository();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(final ClientRegistrationRepository clientRegistrationRepository) {
        var logoutSuccessHandler = new OidcLogoutSuccessHandler(clientRegistrationRepository);
        logoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/");
        return logoutSuccessHandler;
    }
}
