package no.acntech.sandbox.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class FixedDelayGatewayFilter implements GatewayFilter {

    private final Duration delay;

    public FixedDelayGatewayFilter(final Duration delay) {
        Assert.notNull(delay, "Delay is null");
        this.delay = delay;
    }

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        return Mono.just("whatever")
                .delayElement(delay)
                .then(chain.filter(exchange));
    }
}
