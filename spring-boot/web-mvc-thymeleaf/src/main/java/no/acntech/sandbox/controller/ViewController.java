package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }
}
