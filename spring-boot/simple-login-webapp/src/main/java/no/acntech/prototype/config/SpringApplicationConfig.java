package no.acntech.prototype.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan({
        "no.acntech.prototype.controller"
})
@Import({
        SpringWebConfig.class,
        SpringSecurityConfig.class
})
@SpringBootApplication
public class SpringApplicationConfig extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringApplicationConfig.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationConfig.class, args);
    }
}
