package no.acntech.sandbox.greeting.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("no.acntech.sandbox.greeting")
@Configuration(proxyBeanMethods = false)
public class GreetingWorkflowConfig {
}
