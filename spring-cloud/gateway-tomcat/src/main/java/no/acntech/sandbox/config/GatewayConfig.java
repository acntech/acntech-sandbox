package no.acntech.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.socket.client.TomcatWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

@Configuration
public class GatewayConfig implements WebFluxConfigurer {
    //
    //    @Bean
    //    public WebClient webClient() {
    //        return WebClient.builder()
    //                .build();
    //    }
    //
    //    @Primary
    //    @Bean
    //    public WebClientHttpRoutingFilter routingFilter(final WebClient webClient,
    //                                                    final ObjectProvider<List<HttpHeadersFilter>> headersFiltersProvider) {
    //        return new WebClientHttpRoutingFilter(webClient, headersFiltersProvider);
    //    }
    //
    //    @Bean
    //    public WebClientWriteResponseFilter webClientWriteResponseFilter() {
    //        return new WebClientWriteResponseFilter();
    //    }

    @Bean
    public WebSocketClient webSocketClient() {
        return new TomcatWebSocketClient();
    }
}
