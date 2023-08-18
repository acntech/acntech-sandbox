package no.acntech.sandbox.controller;

import no.acntech.sandbox.client.GreetingClient;
import no.acntech.sandbox.model.GreetingFormData;
import no.acntech.sandbox.webservice.greeting.v1.types.SayHelloRequestType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    public ModelAndView indexPagePost(@ModelAttribute("formData") @Valid GreetingFormData formData,
                                      BindingResult bindingResult) {
        var mav = new ModelAndView("index");
        if (bindingResult.hasErrors()) {
            return mav;
        }
        var request = new SayHelloRequestType();
        request.setFirstName(formData.getFirstName());
        var response = greetingClient.sayHello(request);
        mav.addObject("message", "Reply from webservice: " + response.getGreeting());
        return mav;
    }
}
