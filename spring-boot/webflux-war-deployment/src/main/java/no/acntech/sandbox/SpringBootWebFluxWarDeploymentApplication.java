package no.acntech.sandbox;

import no.acntech.sandbox.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.lang.NonNull;
import org.springframework.web.server.adapter.AbstractReactiveWebInitializer;

@SpringBootApplication
public class SpringBootWebFluxWarDeploymentApplication extends AbstractReactiveWebInitializer {

    @NonNull
    @Override
    protected Class<?>[] getConfigClasses() {
        return new Class[]{ApplicationConfig.class};
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebFluxWarDeploymentApplication.class, args);
    }
}
