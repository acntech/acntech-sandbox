package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    @GetMapping(path = "/")
    public ModelAndView indexPage() {
        return new ModelAndView("index");
    }

    @GetMapping(path = "/about")
    public ModelAndView aboutPage() {
        return new ModelAndView("about");
    }

    @GetMapping(path = "/admin")
    public ModelAndView adminPage() {
        return new ModelAndView("admin");
    }

    @GetMapping(path = "/login")
    public String loginPage() {
        return "login";
    }
}
