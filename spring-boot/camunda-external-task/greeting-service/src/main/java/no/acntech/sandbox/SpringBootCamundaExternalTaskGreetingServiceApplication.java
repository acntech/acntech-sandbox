package no.acntech.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import no.acntech.sandbox.config.WorkflowProperties;

@EnableConfigurationProperties({WorkflowProperties.class})
@SpringBootApplication
public class SpringBootCamundaExternalTaskGreetingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCamundaExternalTaskGreetingServiceApplication.class, args);
    }
}
