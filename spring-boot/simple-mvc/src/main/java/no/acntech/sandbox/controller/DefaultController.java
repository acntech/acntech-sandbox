package no.acntech.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class DefaultController {

    @RequestMapping(path = "/", method = GET)
    public String indexPage() {
        return "index";
    }

    @RequestMapping(path = "/{page}", method = GET)
    public String aboutPage(@PathVariable("page") String page) {
        return page;
    }
}
