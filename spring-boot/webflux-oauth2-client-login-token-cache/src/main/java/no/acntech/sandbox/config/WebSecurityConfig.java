package no.acntech.sandbox.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

import no.acntech.sandbox.handler.OidcLogoutSuccessHandler;
import no.acntech.sandbox.repository.CookieAuthorizationRequestRepository;
import no.acntech.sandbox.service.RedisOAuth2AuthorizedClientService;

@EnableWebFluxSecurity
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http,
                                                         final OidcLogoutSuccessHandler logoutSuccessHandler,
                                                         final CookieAuthorizationRequestRepository authorizationRequestRepository) {
        return http
                .authorizeExchange(config -> config
                        .anyExchange().authenticated()
                )
                .logout(config -> config
                        .logoutSuccessHandler(logoutSuccessHandler)
                )
                .oauth2Login(config -> config
                        .authorizationRequestRepository(authorizationRequestRepository))
                .build();
    }

    @Bean
    public CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new CookieAuthorizationRequestRepository();
    }

    @Bean
    public RedisOAuth2AuthorizedClientService redisOAuth2AuthorizedClientService(
            final ReactiveClientRegistrationRepository clientRegistrationRepository,
            @Qualifier("authorizedClientRedisTemplate") final ReactiveRedisTemplate<String, OAuth2AuthorizedClient> redisTemplate) {
        return new RedisOAuth2AuthorizedClientService(clientRegistrationRepository, redisTemplate);
    }

    @Bean
    public OidcLogoutSuccessHandler oidcLogoutSuccessHandler(final ReactiveClientRegistrationRepository clientRegistrationRepository) {
        var logoutSuccessHandler = new OidcLogoutSuccessHandler(clientRegistrationRepository);
        logoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/");
        return logoutSuccessHandler;
    }
}
