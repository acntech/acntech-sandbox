package no.acntech.sandbox.controller;

import no.acntech.sandbox.webservice.simple.v1_0.wsdl.SimplePortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping(path = "/")
@Controller
public class DefaultController {

    private final SimplePortType simpleConsumer;

    @Autowired
    public DefaultController(final SimplePortType simpleConsumer) {
        this.simpleConsumer = simpleConsumer;
    }

    @RequestMapping(method = GET)
    public String indexPage() {
        return "index";
    }

    @RequestMapping(method = POST)
    public ModelAndView indexPagePost(@RequestParam("firstName") String firstName) {
        ModelAndView mav = new ModelAndView("index");
        String message = simpleConsumer.sayHello(firstName);
        mav.addObject("message", "Reply from webservice: " + message);
        return mav;
    }
}
