package no.acntech.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.reactive.ReactiveOAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;

@SpringBootApplication(exclude = {
        OAuth2ClientAutoConfiguration.class,
        ReactiveOAuth2ClientAutoConfiguration.class
})
public class SpringBootLoginBasicAuthOidcWebClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLoginBasicAuthOidcWebClientApplication.class, args);
    }
}
