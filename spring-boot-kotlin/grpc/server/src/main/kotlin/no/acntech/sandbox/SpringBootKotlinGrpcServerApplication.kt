package no.acntech.sandbox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootKotlinGrpcServerApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinGrpcServerApplication>(*args)
}
