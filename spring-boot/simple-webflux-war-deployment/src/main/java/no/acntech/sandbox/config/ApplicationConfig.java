package no.acntech.sandbox.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@EnableWebFlux
@ComponentScan({"no.acntech.sandbox"})
@Configuration
public class ApplicationConfig implements WebFluxConfigurer {
}
