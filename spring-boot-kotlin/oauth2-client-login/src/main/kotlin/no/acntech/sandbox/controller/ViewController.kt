package no.acntech.sandbox.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController {

    @GetMapping(path = ["/"])
    fun getIndexPage(): String {
        return "index"
    }

    @GetMapping(path = ["/about"])
    fun getAboutPage(): String {
        return "about"
    }
}