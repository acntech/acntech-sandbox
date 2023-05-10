package no.acntech.sandbox.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration(proxyBeanMethods = false)
public class WebClientConfig {

    @Bean
    public WebClient webClient(final WebClient.Builder webClientBuilder,
                               @Value("${app.file.url}") String fileUrl) {
        return webClientBuilder
                .baseUrl(fileUrl)
                .build();
    }
}
