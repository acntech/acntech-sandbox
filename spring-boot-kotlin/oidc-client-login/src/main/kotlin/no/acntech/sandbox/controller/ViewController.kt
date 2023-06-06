package no.acntech.sandbox.controller

import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class ViewController {

    @GetMapping(path = ["/"])
    fun getHomePage(authentication: Authentication): ModelAndView {
        val model = getModel(authentication)
        return ModelAndView("home", model)
    }

    @GetMapping(path = ["/about"])
    fun getAboutPage(authentication: Authentication): ModelAndView {
        val model = getModel(authentication)
        return ModelAndView("about", model)
    }

    private fun getModel(authentication: Authentication): Map<String, Any?> {
        val model = LinkedHashMap<String, Any?>()
        val oAuth2User = authentication.principal as OAuth2User
        model["userInfo"] = oAuth2User.attributes
        return model
    }
}