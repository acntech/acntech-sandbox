package no.acntech.sandbox.controller;

import no.acntech.sandbox.model.FormData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {

    @GetMapping(path = "/")
    public String getHomePage(final Model model) {
        model.addAttribute("formData", new FormData());
        return "home";
    }

    @PostMapping(path = "/")
    public String postHomePage(@ModelAttribute("formData") final FormData formData,
                               final Model model) {
        final var name = StringUtils.hasText(formData.getName()) ? formData.getName() : "Unknown";
        model.addAttribute("formData", formData);
        model.addAttribute("greeting", "Hello " + name + "!");
        return "home";
    }

    @GetMapping(path = "/about")
    public String getAboutPage() {
        return "about";
    }
}
