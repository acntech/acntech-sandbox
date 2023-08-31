package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import no.acntech.sandbox.service.GreetingService;

@Controller
public class ViewController {

    private final GreetingService greetingService;

    public ViewController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping(path = "/")
    public String getIndexPage(final Model model) {
        final var greetings = greetingService.findGreetings();
        model.addAttribute("greetings", greetings);
        return "index";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }
}
