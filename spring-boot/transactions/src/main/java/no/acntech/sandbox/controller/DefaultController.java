package no.acntech.sandbox.controller;

import no.acntech.sandbox.dto.CreateFoo;
import no.acntech.sandbox.service.DefaultService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class DefaultController {

    private final DefaultService defaultService;

    public DefaultController(final DefaultService defaultService) {
        this.defaultService = defaultService;
    }

    @GetMapping(path = "/")
    public ModelAndView getIndex() {
        return new ModelAndView("index");
    }

    @GetMapping(path = "/foo")
    public ModelAndView getFoo() {
        ModelAndView mav = new ModelAndView("foo");
        mav.addObject("items", defaultService.readAllFoo());
        return mav;
    }

    @PostMapping(path = "/foo")
    public ModelAndView postFoo(@Valid @ModelAttribute("foo") final CreateFoo createFoo) {
        defaultService.createFoo(createFoo);
        ModelAndView mav = new ModelAndView("foo");
        mav.addObject("items", defaultService.readAllFoo());
        return mav;
    }

    @GetMapping(path = "/bar")
    public ModelAndView getBar() {
        ModelAndView mav = new ModelAndView("bar");
        mav.addObject("items", defaultService.readAllBar());
        return mav;
    }
}
