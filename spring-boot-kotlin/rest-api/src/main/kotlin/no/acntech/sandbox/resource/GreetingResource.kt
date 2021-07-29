package no.acntech.sandbox.resource

import no.acntech.sandbox.model.Greeting
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping(path = ["greetings"])
@RestController
class GreetingResource {

    fun get(@RequestParam(name = "name", defaultValue = "Nobody") name: String): ResponseEntity<Greeting> {
        return ResponseEntity.ok(Greeting(name))
    }
}