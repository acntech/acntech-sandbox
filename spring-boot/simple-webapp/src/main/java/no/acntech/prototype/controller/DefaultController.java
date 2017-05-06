package no.acntech.prototype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping(path = "/")
@Controller
public class DefaultController {

    @RequestMapping(method = GET)
    public String indexPage() {
        return "index";
    }
}
