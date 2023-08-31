package no.acntech.sandbox.controller;

import no.acntech.sandbox.model.FormData;
import no.acntech.sandbox.service.GreetingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Duration;
import java.time.Instant;

@Controller
public class ViewController {

    private final Duration cacheTimeToLive;
    private final GreetingService greetingService;

    public ViewController(@Value("${app.cache.default-time-to-live}") final Duration cacheTimeToLive,
                          final GreetingService greetingService) {
        this.cacheTimeToLive = cacheTimeToLive;
        this.greetingService = greetingService;
    }

    @GetMapping("/")
    public String getIndexPage(final Model model) {
        model.addAttribute("formData", new FormData());
        model.addAttribute("greeting", null);
        model.addAttribute("created", null);
        model.addAttribute("fetched", null);
        model.addAttribute("expiry", null);
        return "index";
    }

    @PostMapping("/")
    public String postIndexPage(@ModelAttribute("formData") final FormData formData,
                                final Model model) {
        final var name = StringUtils.isNoneBlank(formData.getName()) ? formData.getName() : "Unknown";
        final var greeting = greetingService.getGreeting(name);
        final var createdDuration = java.time.Duration.between(greeting.created(), Instant.now());
        final var fetchedDuration = java.time.Duration.between(greeting.fetched(), Instant.now());
        model.addAttribute("formData", new FormData());
        model.addAttribute("greeting", greeting);
        model.addAttribute("created", createdDuration.getSeconds());
        model.addAttribute("fetched", fetchedDuration.getSeconds());
        model.addAttribute("expiry", cacheTimeToLive.getSeconds() - fetchedDuration.getSeconds());
        return "index";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }
}
