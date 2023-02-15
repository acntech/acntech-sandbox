package no.acntech.sandbox.controller;

import jakarta.validation.Valid;
import no.acntech.sandbox.model.FormData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String getIndexPage(final Model model) {
        model.addAttribute("formData", new FormData());
        return "index";
    }

    @PostMapping("/")
    public String postIndexPage(@ModelAttribute("formData") @Valid final FormData formData,
                                final BindingResult bindingResult,
                                final Model model) {
        model.addAttribute("formData", formData);
        if (!bindingResult.hasErrors()) {
            final var greeting = "Hello " + formData.getName() + "!";
            model.addAttribute("greeting", greeting);
        }
        return "index";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }
}
