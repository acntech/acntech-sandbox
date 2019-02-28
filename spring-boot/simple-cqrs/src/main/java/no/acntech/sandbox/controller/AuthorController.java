package no.acntech.sandbox.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import no.acntech.sandbox.command.service.AuthorCommandService;
import no.acntech.sandbox.entity.Author;
import no.acntech.sandbox.query.service.AuthorQueryService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping(path = "/authors")
@Controller
public class AuthorController {

    private static final String VIEW_NAME = "authors";
    private static final String ERROR_VIEW_NAME = "error";
    private static final String REQUEST_PARAM_KEY = "list";
    private static final String REQUEST_PARAM_VALUE = "authors";
    private static final String AUTHOR_MODEL_ATTR = "author";
    private static final String AUTHORS_MODEL_ATTR = "authors";

    private final AuthorQueryService queryService;
    private final AuthorCommandService commandService;

    @Autowired
    public AuthorController(final AuthorQueryService queryService, final AuthorCommandService commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @GetMapping
    public ModelAndView get(@RequestParam(value = REQUEST_PARAM_KEY, required = false) String list) {
        ModelAndView modelAndView = new ModelAndView(VIEW_NAME);
        modelAndView.addObject(AUTHOR_MODEL_ATTR, new Author());
        if (REQUEST_PARAM_VALUE.equals(list)) {
            modelAndView.addObject(AUTHORS_MODEL_ATTR, queryService.findAuthors());
        }
        return modelAndView;
    }

    @PostMapping
    public ModelAndView post(@Valid @ModelAttribute(AUTHOR_MODEL_ATTR) Author author, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView(ERROR_VIEW_NAME);
        }
        commandService.createAuthor(author);
        return get(null);
    }
}
