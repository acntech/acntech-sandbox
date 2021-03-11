package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import no.acntech.sandbox.client.GreetingClient;

@RequestMapping(path = "/")
@Controller
public class DefaultController {

    private final GreetingClient greetingClient;

    public DefaultController(final GreetingClient greetingClient) {
        this.greetingClient = greetingClient;
    }

    @GetMapping
    public String indexPage() {
        return "index";
    }

    @PostMapping
    public ModelAndView indexPagePost(@RequestParam("firstName") String firstName) {
        ModelAndView mav = new ModelAndView("index");
        String message = greetingClient.sayHello(firstName);
        mav.addObject("message", "Reply from webservice: " + message);
        return mav;
    }
}
