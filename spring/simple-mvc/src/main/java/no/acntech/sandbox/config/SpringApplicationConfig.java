package no.acntech.sandbox.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("no.acntech.sandbox.controller")
@PropertySource("classpath:application.properties")
@Import(SpringWebConfig.class)
@Configuration
public class SpringApplicationConfig {
}
