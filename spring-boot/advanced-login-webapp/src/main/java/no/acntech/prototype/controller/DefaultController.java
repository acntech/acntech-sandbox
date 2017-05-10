package no.acntech.prototype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class DefaultController {

    @RequestMapping(path = "/", method = GET)
    public String indexPage() {
        return "index";
    }
/*
    @RequestMapping(path = "/login", method = GET)
    public String loginPage() {
        return "login";
    }*/
}