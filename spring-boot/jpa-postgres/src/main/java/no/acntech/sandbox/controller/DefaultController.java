package no.acntech.sandbox.controller;

import no.acntech.sandbox.repository.FooRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    private final FooRepository fooRepository;

    public DefaultController(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @GetMapping(path = "/")
    public ModelAndView indexPage() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("data", fooRepository.findAll());
        return mav;
    }
}
