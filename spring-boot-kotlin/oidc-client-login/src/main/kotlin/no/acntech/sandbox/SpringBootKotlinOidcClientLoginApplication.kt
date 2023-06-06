package no.acntech.sandbox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootKotlinOidcClientLoginApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinOidcClientLoginApplication>(*args)
}
