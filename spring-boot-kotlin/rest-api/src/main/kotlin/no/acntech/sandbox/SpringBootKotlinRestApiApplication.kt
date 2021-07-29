package no.acntech.sandbox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootKotlinRestApiApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinRestApiApplication>(*args)
}
