package no.acntech.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootSimpleOauth2ClientLoginApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSimpleOauth2ClientLoginApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootSimpleOauth2ClientLoginApplication.class);
    }
}
