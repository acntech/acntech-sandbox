package no.acntech.sandbox.webapp;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableProcessApplication
@SpringBootApplication
public class SpringBootCamundaMultiModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCamundaMultiModuleApplication.class, args);
    }
}
