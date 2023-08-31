package no.acntech.sandbox.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import no.acntech.sandbox.model.FormData;
import no.acntech.sandbox.model.GreetingEvent;
import no.acntech.sandbox.service.GreetingCommandService;
import no.acntech.sandbox.service.GreetingQueryService;

@Controller
public class ViewController {

    private final GreetingQueryService queryService;
    private final GreetingCommandService commandService;

    public ViewController(final GreetingQueryService queryService,
                          final GreetingCommandService commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @GetMapping("/")
    public String getIndexPage(final Model model) {
        final var greetings = queryService.findGreetings();
        model.addAttribute("formData", new FormData());
        model.addAttribute("greetings", greetings);
        return "index";
    }

    @PostMapping("/")
    public String postIndexPage(@ModelAttribute("formData") final FormData formData,
                                final Model model) {
        final var name = StringUtils.isNoneBlank(formData.getName()) ? formData.getName() : "Unknown";
        commandService.createGreeting(new GreetingEvent("Hello " + name + "!"));
        final var greetings = queryService.findGreetings();
        model.addAttribute("formData", new FormData());
        model.addAttribute("greetings", greetings);
        return "index";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }
}
