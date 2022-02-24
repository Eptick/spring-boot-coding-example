package hr.redzicleon.library.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.services.AuthorsBooksService;

@RestController
@RequestMapping("authors/{uuid}/books")
public class AuthorsBooksController {
    AuthorsBooksService authorsBooksService;

    public AuthorsBooksController(AuthorsBooksService authorsBooksService) {
        this.authorsBooksService = authorsBooksService;
    }

    @PostMapping("{isbn}")
    public Author assignBookToAuthor(@PathVariable("uuid") UUID uuid, @PathVariable("isbn") String ISBN) {
        return this.authorsBooksService.assignBookToAuthor(uuid, ISBN);
    }
    
}
