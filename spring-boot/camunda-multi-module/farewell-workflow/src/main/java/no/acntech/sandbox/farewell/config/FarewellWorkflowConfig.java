package no.acntech.sandbox.farewell.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("no.acntech.sandbox.farewell")
@Configuration(proxyBeanMethods = false)
public class FarewellWorkflowConfig {
}
