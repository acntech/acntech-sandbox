package no.acntech.sandbox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import no.acntech.sandbox.service.UserService;

@Controller
public class ViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewController.class);
    private final UserService userService;

    public ViewController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/")
    public String indexPage() {
        return "index";
    }

    @GetMapping(path = "/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping(path = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(path = "/admin")
    public String adminPage(final Model model) {
        final var users = userService.findUsers();
        LOGGER.info("Found {} users", users.size());
        model.addAttribute("users", users);
        return "admin";
    }
}
