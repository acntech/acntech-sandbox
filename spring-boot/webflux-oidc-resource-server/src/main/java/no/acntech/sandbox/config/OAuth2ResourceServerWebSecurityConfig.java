package no.acntech.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@EnableWebFluxSecurity
public class OAuth2ResourceServerWebSecurityConfig {

    @Bean
    public SecurityWebFilterChain filterChain(final ServerHttpSecurity http) {
        return http
                .authorizeExchange(config -> config
                        .anyExchange()
                        .authenticated()
                )
                .oauth2ResourceServer(config -> config
                        .jwt(Customizer.withDefaults())
                )
                .build();
    }

    @Bean
    public WebClient webClient(final ReactiveClientRegistrationRepository clientRegistrationRepository, final ServerOAuth2AuthorizedClientRepository authorizedClientRepository) {
        ServerOAuth2AuthorizedClientExchangeFilterFunction filter =
                new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, authorizedClientRepository);
        return WebClient.builder()
                .filter(filter)
                .build();
    }
}
