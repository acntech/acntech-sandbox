package no.acntech.sandbox.controller;

import no.acntech.sandbox.webservice.simple.v1.SimplePortType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(path = "/")
@Controller
public class DefaultController {

    private final SimplePortType simpleConsumer;

    public DefaultController(final SimplePortType simpleConsumer) {
        this.simpleConsumer = simpleConsumer;
    }

    @GetMapping
    public String indexPage() {
        return "index";
    }

    @PostMapping
    public ModelAndView indexPagePost(@RequestParam("firstName") String firstName) {
        ModelAndView mav = new ModelAndView("index");
        String message = simpleConsumer.sayHello(firstName);
        mav.addObject("message", "Reply from webservice: " + message);
        return mav;
    }
}
