package no.acntech.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(
        basePackages = {"no.acntech.sandbox.repository"})
@EntityScan(
        basePackageClasses = {Jsr310JpaConverters.class},
        basePackages = {"no.acntech.sandbox.model"})
@EnableTransactionManagement
@SpringBootApplication
public class SpringBootTransactionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTransactionsApplication.class, args);
    }
}
