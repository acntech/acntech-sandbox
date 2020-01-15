package no.acntech.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.lang.NonNull;
import org.springframework.web.server.adapter.AbstractReactiveWebInitializer;

import no.acntech.sandbox.config.GatewayConfig;

@SpringBootApplication
public class SpringCloudGatewayTomcatApplication extends AbstractReactiveWebInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayTomcatApplication.class, args);
    }

    @NonNull
    @Override
    protected Class<?>[] getConfigClasses() {
        return new Class[]{GatewayConfig.class};
    }
}
