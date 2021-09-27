package no.acntech.sandbox.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import no.acntech.sandbox.farewell.config.FarewellWorkflowConfig;
import no.acntech.sandbox.greeting.config.GreetingWorkflowConfig;

@Import({
        GreetingWorkflowConfig.class,
        FarewellWorkflowConfig.class
})
@Configuration(proxyBeanMethods = false)
public class ApplicationConfig {
}
