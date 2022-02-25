package hr.redzicleon.library.controllers;

import java.util.UUID;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.Book;
import hr.redzicleon.library.services.AuthorsBooksService;

@RestController
@RequestMapping("authors/{uuid}/books")
public class AuthorsBooksController {
    AuthorsBooksService authorsBooksService;

    public AuthorsBooksController(AuthorsBooksService authorsBooksService) {
        this.authorsBooksService = authorsBooksService;
    }

    @GetMapping("")
    public Page<Book> getBooksByAuthor(@QuerydslPredicate(root = Book.class) Predicate predicate, @PathVariable("uuid") UUID uuid, Pageable pageable) {
        return this.authorsBooksService.findAllBooksByAuthor(uuid, pageable, predicate);
    }

    @PostMapping("{isbn}")
    public Author assignBookToAuthor(@PathVariable("uuid") UUID uuid, @PathVariable("isbn") String ISBN) {
        return this.authorsBooksService.assignBookToAuthor(uuid, ISBN);
    }

    @DeleteMapping("{isbn}")
    public Author removeBookFromAuthor(@PathVariable("uuid") UUID uuid, @PathVariable("isbn") String ISBN) {
        return this.authorsBooksService.deleteBookFromAuthor(uuid, ISBN);
    }
    
}
