package no.acntech.sandbox.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import no.acntech.sandbox.handler.OidcLogoutSuccessHandler;
import no.acntech.sandbox.repository.CookieAuthorizationRequestRepository;
import no.acntech.sandbox.service.RedisOAuth2AuthorizedClientService;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http,
                                                   final OidcLogoutSuccessHandler logoutSuccessHandler,
                                                   final CookieAuthorizationRequestRepository authorizationRequestRepository) throws Exception {
        return http
                .authorizeHttpRequests(config -> config
                        .anyRequest()
                        .authenticated()
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
    public CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new CookieAuthorizationRequestRepository();
    }

    @Bean
    public RedisOAuth2AuthorizedClientService redisOAuth2AuthorizedClientService(
            final ClientRegistrationRepository clientRegistrationRepository,
            @Qualifier("authorizedClientRedisTemplate") final RedisTemplate<String, OAuth2AuthorizedClient> redisTemplate) {
        return new RedisOAuth2AuthorizedClientService(clientRegistrationRepository, redisTemplate);
    }

    @Bean
    public OidcLogoutSuccessHandler oidcLogoutSuccessHandler(final ClientRegistrationRepository clientRegistrationRepository) {
        var logoutSuccessHandler = new OidcLogoutSuccessHandler(clientRegistrationRepository);
        logoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/");
        return logoutSuccessHandler;
    }
}
