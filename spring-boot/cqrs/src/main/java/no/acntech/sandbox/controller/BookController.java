package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(path = "/books")
@Controller
public class BookController {

    private static final String VIEW_NAME = "books";

    @GetMapping
    public ModelAndView get() {
        return new ModelAndView(VIEW_NAME);
    }
}
