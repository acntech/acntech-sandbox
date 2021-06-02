package no.acntech.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootAccessControlPermissionEvaluatorApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootAccessControlPermissionEvaluatorApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAccessControlPermissionEvaluatorApplication.class, args);
    }
}
