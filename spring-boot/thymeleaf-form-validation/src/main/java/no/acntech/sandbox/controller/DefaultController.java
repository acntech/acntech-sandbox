package no.acntech.sandbox.controller;

import no.acntech.sandbox.model.FormData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String getIndexPage(final Model model) {
        model.addAttribute("formData", new FormData());
        return "index";
    }

    @PostMapping("/")
    public String postIndexPage(@ModelAttribute("formData") final FormData formData,
                                final Model model) {
        final var name = StringUtils.isNoneBlank(formData.getName()) ? formData.getName() : "Unknown";
        final var greeting = "Hello " + name + "!";
        model.addAttribute("formData", formData);
        model.addAttribute("greeting", greeting);
        return "index";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }
}
