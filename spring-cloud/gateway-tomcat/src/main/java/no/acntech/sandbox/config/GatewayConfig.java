package no.acntech.sandbox.config;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.WebClientHttpRoutingFilter;
import org.springframework.cloud.gateway.filter.WebClientWriteResponseFilter;
import org.springframework.cloud.gateway.filter.headers.HttpHeadersFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.socket.client.TomcatWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

@Configuration
public class GatewayConfig {
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
