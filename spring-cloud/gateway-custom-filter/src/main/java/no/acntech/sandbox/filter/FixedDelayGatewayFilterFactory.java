package no.acntech.sandbox.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.Duration;

@Component
public class FixedDelayGatewayFilterFactory extends AbstractGatewayFilterFactory<FixedDelayGatewayFilterFactory.Config> {

    public FixedDelayGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(final Config config) {
        config.validate();
        return new FixedDelayGatewayFilter(config.getDelay());
    }

    public static class Config {

        private Duration delay;

        public void validate() {
            Assert.notNull(delay, "Delay is null or blank");
        }

        public Duration getDelay() {
            return delay;
        }

        public void setDelay(Duration delay) {
            this.delay = delay;
        }
    }
}
