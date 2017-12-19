package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import no.acntech.sandbox.repository.FooRepository;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping(path = "/")
@Controller
public class DefaultController {

    private final FooRepository fooRepository;

    public DefaultController(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @RequestMapping(method = GET)
    public ModelAndView indexPage() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("data", fooRepository.findAll());
        return mav;
    }
}
