package no.acntech.prototype.domain.user.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping(path = "/user")
@Controller
public class UserController {

    @RequestMapping(method = GET)
    public String get(Map<String, Object> model) {
        return "user";
    }
}
