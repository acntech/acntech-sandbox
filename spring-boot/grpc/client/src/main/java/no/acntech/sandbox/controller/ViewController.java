package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import no.acntech.sandbox.model.Hello;
import no.acntech.sandbox.service.GreetingService;

@Controller
public class ViewController {

    private final GreetingService greetingService;

    public ViewController(final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping(path = "/")
    public ModelAndView getHomePage() {
        var mav = new ModelAndView("home");
        mav.addObject("hello", new Hello());
        return mav;
    }

    @PostMapping(path = "/")
    public ModelAndView postHomePage(@ModelAttribute Hello hello) {
        var greeting = greetingService.getGreeting(hello);
        var mav = new ModelAndView("home");
        mav.addObject("hello", new Hello());
        mav.addObject("greeting", greeting);
        return mav;
    }

    @GetMapping(path = "/about")
    public ModelAndView getAboutPage() {
        return new ModelAndView("about");
    }
}
