package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import no.acntech.sandbox.webservice.greeting.v1.GreetingPortType;

@RequestMapping(path = "/")
@Controller
public class DefaultController {

    private final GreetingPortType greetingPortType;

    public DefaultController(final GreetingPortType greetingPortType) {
        this.greetingPortType = greetingPortType;
    }

    @GetMapping
    public String indexPage() {
        return "index";
    }

    @PostMapping
    public ModelAndView indexPagePost(@RequestParam("firstName") String firstName) {
        ModelAndView mav = new ModelAndView("index");
        String message = greetingPortType.sayHello(firstName);
        mav.addObject("message", "Reply from webservice: " + message);
        return mav;
    }
}
