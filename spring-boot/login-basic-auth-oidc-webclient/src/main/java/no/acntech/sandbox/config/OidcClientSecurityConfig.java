package no.acntech.sandbox.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientPropertiesMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@EnableConfigurationProperties(OAuth2ClientProperties.class)
@Configuration(proxyBeanMethods = false)
public class OidcClientSecurityConfig {

    @Bean
    public WebClient oidcEnabledWebClient(final WebClient.Builder webClientBuilder,
                                          final ReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
        final var oauth2 = new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        oauth2.setDefaultClientRegistrationId("generic-client");
        return webClientBuilder
                .filter(oauth2)
                .build();
    }

    @Bean
    public ReactiveOAuth2AuthorizedClientManager authorizedClientManager(final ReactiveClientRegistrationRepository clientRegistrationRepository,
                                                                         final ReactiveOAuth2AuthorizedClientService authorizedClientService) {
        return new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientService);
    }

    @Bean
    public ReactiveClientRegistrationRepository clientRegistrationRepository(final OAuth2ClientProperties properties) {
        final var clientPropertiesMapper = new OAuth2ClientPropertiesMapper(properties);
        final var clientRegistrations = new ArrayList<>(clientPropertiesMapper.asClientRegistrations().values());
        return new InMemoryReactiveClientRegistrationRepository(clientRegistrations);
    }

    @Bean
    public ReactiveOAuth2AuthorizedClientService authorizedClientService(final ReactiveClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistrationRepository);
    }
}
