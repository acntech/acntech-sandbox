package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    @GetMapping(path = "/")
    public ModelAndView getIndexPage() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", null);
        return modelAndView;
    }

    @PostMapping(path = "/")
    public ModelAndView postIndexPage(@RequestParam(value = "name", required = false) String name) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", StringUtils.hasText(name) ? name : "Nobody");
        return modelAndView;
    }

    @GetMapping(path = "/about")
    public String getAboutPage() {
        return "about";
    }
}
