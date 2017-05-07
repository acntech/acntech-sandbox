package no.acntech.prototype.controller.user;

import no.acntech.prototype.entity.user.User;
import no.acntech.prototype.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping(path = "/user")
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = GET)
    public ModelAndView get() {
        List<User> users = new ArrayList<>();
        Iterable<User> result = userRepository.findAll();
        result.forEach(users::add);
        LOGGER.info("Found {} users", users.size());
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("users", users);
        return mav;
    }
}
