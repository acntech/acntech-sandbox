package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/")
@Controller
public class DefaultController {

    @GetMapping
    public String indexPage() {
        return "index";
    }
}
