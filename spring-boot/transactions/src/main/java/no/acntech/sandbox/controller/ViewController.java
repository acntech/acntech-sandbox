package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import no.acntech.sandbox.model.FormData;
import no.acntech.sandbox.service.GreetingService;

@Controller
public class ViewController {

    private final GreetingService greetingService;

    public ViewController(final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping(path = "/")
    public String getIndexPage(final Model model) {
        final var greetings = greetingService.findGreetings();
        model.addAttribute("formData", new FormData());
        model.addAttribute("greetings", greetings);
        return "index";
    }

    @PostMapping(path = "/")
    public String postIndexPage(@ModelAttribute FormData formData,
                                final Model model) {
        greetingService.createGreeting(formData.getName());
        final var greetings = greetingService.findGreetings();
        model.addAttribute("formData", formData);
        model.addAttribute("greetings", greetings);
        return "index";
    }

    @GetMapping(path = "/about")
    public String getAboutPage() {
        return "about";
    }
}
