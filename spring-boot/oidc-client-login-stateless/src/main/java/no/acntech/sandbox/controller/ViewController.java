package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @GetMapping(path = "/")
    public ModelAndView indexPage() {
        return new ModelAndView("index");
    }

    @GetMapping(path = "/about")
    public ModelAndView aboutPage() {
        return new ModelAndView("about");
    }
}
