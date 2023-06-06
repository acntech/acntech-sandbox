package no.acntech.sandbox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootKotlinGrpcClientApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinGrpcClientApplication>(*args)
}
