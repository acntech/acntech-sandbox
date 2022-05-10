package no.acntech.sandbox.controller;

import no.acntech.sandbox.model.Hello;
import no.acntech.sandbox.service.GreetingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    private final GreetingService greetingService;

    public DefaultController(final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping(path = "/")
    public ModelAndView getIndexPage() {
        var mav = new ModelAndView("index");
        mav.addObject("hello", new Hello());
        return mav;
    }

    @PostMapping(path = "/")
    public ModelAndView postIndexPage(@ModelAttribute Hello hello) {
        var greeting = greetingService.getGreeting(hello);
        var mav = new ModelAndView("index");
        mav.addObject("hello", new Hello());
        mav.addObject("greeting", greeting);
        return mav;
    }

    @GetMapping(path = "/about")
    public ModelAndView getAboutPage() {
        return new ModelAndView("about");
    }
}
