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
        model.addAttribute("formData", new FormData());
        model.addAttribute("greeting", null);
        return "index";
    }

    @PostMapping(path = "/")
    public String postIndexPage(@ModelAttribute FormData formData,
                                final Model model) {
        final var greeting = greetingService.getGreeting(formData);
        model.addAttribute("formData", new FormData());
        model.addAttribute("greeting", greeting);
        return "index";
    }

    @GetMapping(path = "/about")
    public String getAboutPage() {
        return "about";
    }
}
