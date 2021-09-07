package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import no.acntech.sandbox.repository.GreetingRepository;

@Controller
public class DefaultController {

    private final GreetingRepository greetingRepository;

    public DefaultController(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @GetMapping(path = "/")
    public ModelAndView indexPage() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("greetings", greetingRepository.findAll());
        return mav;
    }

    @GetMapping("/about")
    public ModelAndView aboutPage() {
        return new ModelAndView("about");
    }
}
