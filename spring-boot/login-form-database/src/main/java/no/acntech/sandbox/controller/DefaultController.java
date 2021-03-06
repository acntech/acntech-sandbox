package no.acntech.sandbox.controller;

import no.acntech.sandbox.domain.User;
import no.acntech.sandbox.domain.UserModel;
import no.acntech.sandbox.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

    private final UserRepository userRepository;

    public DefaultController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/")
    public String indexPage() {
        return "index";
    }

    @GetMapping(path = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(path = "/users")
    public ModelAndView userPage() {
        List<UserModel> users = new ArrayList<>();
        Iterable<User> result = userRepository.findAll();
        result.forEach(user -> users.add(UserModel.builder(user).build()));
        LOGGER.info("Found {} users", users.size());
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", users);
        return mav;
    }
}
