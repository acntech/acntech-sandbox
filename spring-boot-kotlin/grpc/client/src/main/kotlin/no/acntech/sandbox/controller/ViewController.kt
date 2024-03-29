package no.acntech.sandbox.controller

import no.acntech.sandbox.model.Greeting
import no.acntech.sandbox.model.Hello
import no.acntech.sandbox.service.GreetingService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class ViewController(val greetingService: GreetingService) {

    @GetMapping(path = ["/"])
    fun getHomePage(): ModelAndView {
        val mav = ModelAndView("home")
        mav.addObject("hello", Hello())
        return mav
    }

    @PostMapping(path = ["/"])
    fun postHomePage(@ModelAttribute hello: Hello): ModelAndView {
        val greeting: Greeting = greetingService.getGreeting(hello)
        val mav = ModelAndView("home")
        mav.addObject("hello", Hello())
        mav.addObject("greeting", greeting)
        return mav
    }

    @GetMapping(path = ["/about"])
    fun getAboutPage(): ModelAndView {
        return ModelAndView("about")
    }
}