package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping(path = "/")
    public String indexPage() {
        return "index";
    }

    @GetMapping(path = "/login")
    public String loginPage() {
        return "login";
    }
}
