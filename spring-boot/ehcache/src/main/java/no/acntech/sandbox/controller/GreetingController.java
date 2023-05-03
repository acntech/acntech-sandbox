package no.acntech.sandbox.controller;

import no.acntech.sandbox.model.Greeting;
import no.acntech.sandbox.service.GreetingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.time.LocalDateTime;

@Controller
public class GreetingController {

    private final GreetingService simpleService;

    public GreetingController(final GreetingService simpleService) {
        this.simpleService = simpleService;
    }

    @RequestMapping(path = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView indexPage(@RequestParam(value = "firstName", required = false) String firstName) {
        ModelAndView mav = new ModelAndView("index");
        if (firstName != null && !firstName.isEmpty()) {
            Greeting greeting = simpleService.getGreeting(firstName);
            Duration duration = Duration.between(greeting.getTimestamp(), LocalDateTime.now());
            mav.addObject("greeting", greeting.getGreeting());
            mav.addObject("created", duration.getSeconds());
            mav.addObject("expiry", 60 - duration.getSeconds());
        }
        return mav;
    }
}
