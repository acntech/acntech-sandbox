package no.acntech.sandbox.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ViewController {

    @GetMapping(path = "/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping(path = "/about")
    public String getAboutPage() {
        return "about";
    }

    @ModelAttribute
    public void getModel(final Authentication authentication,
                         final Model model) {
        model.addAttribute("authentication", authentication);
    }
}
