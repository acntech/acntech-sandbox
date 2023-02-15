package no.acntech.sandbox.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class DefaultController {

    @GetMapping(path = "/")
    public ModelAndView homePage(final Authentication authentication) {
        final var model = getModel(authentication);
        return new ModelAndView("home", model);
    }

    @GetMapping(path = "/about")
    public ModelAndView aboutPage(final Authentication authentication) {
        final var model = getModel(authentication);
        return new ModelAndView("about", model);
    }

    private Map<String, Object> getModel(final Authentication authentication) {
        final var model = new LinkedHashMap<String, Object>();
        if (authentication.getPrincipal() instanceof OAuth2User oAuth2User) {
            model.put("userInfo", oAuth2User.getAttributes());
        }
        return model;
    }
}
