package no.acntech.prototype.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan({
        "no.acntech.prototype.entity"
})
@EnableJpaRepositories({
        "no.acntech.prototype.repository"
})
@ComponentScan({
        "no.acntech.prototype.controller",
        "no.acntech.prototype.service"
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
