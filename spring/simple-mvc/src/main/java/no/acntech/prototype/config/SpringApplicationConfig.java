package no.acntech.prototype.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = {
        "no.acntech.prototype.controller"
})
@PropertySource("classpath:config/application.properties")
@Import({
        SpringWebConfig.class
})
@Configuration
public class SpringApplicationConfig {
}
