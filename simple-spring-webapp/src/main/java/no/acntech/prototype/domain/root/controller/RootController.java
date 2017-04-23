package no.acntech.prototype.domain.root.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping(path = "/")
@Controller
public class RootController {

    @RequestMapping(method = GET)
    public String get(Map<String, Object> model) {
        return "index";
    }
}
