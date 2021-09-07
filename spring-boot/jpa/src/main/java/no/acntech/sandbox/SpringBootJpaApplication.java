package no.acntech.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
        basePackages = {"no.acntech.sandbox.repository"})
@EntityScan(
        basePackageClasses = {Jsr310JpaConverters.class},
        basePackages = {"no.acntech.sandbox.domain"})
@SpringBootApplication
public class SpringBootJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaApplication.class, args);
    }
}
