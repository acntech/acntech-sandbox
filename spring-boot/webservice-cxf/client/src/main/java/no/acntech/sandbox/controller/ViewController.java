package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import no.acntech.sandbox.client.GreetingClient;
import no.acntech.sandbox.model.FormData;
import no.acntech.sandbox.model.GreetingDto;
import no.acntech.sandbox.webservice.greeting.v1.types.SayHelloRequestType;

@Controller
public class ViewController {

    private final GreetingClient greetingClient;

    public ViewController(final GreetingClient greetingClient) {
        this.greetingClient = greetingClient;
    }

    @GetMapping(path = "/")
    public String getIndexPage(final Model model) {
        model.addAttribute("formData", new FormData());
        model.addAttribute("greeting", null);
        return "index";
    }

    @PostMapping(path = "/")
    public String postIndexPage(@ModelAttribute FormData formData,
                                final Model model) {
        final var request = new SayHelloRequestType();
        request.setFirstName(formData.getName());
        final var response = greetingClient.sayHello(request);
        model.addAttribute("formData", new FormData());
        model.addAttribute("greeting", new GreetingDto(response.getGreeting()));
        return "index";
    }

    @GetMapping(path = "/about")
    public String getAboutPage() {
        return "about";
    }
}
