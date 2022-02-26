package hr.redzicleon.library.controllers;

import java.util.UUID;

import com.querydsl.core.types.Predicate;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.redzicleon.library.annotations.ApiVersion;
import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.Book;
import hr.redzicleon.library.services.BooksAuthorsService;

/**
 * Operations on modifying the relationship between books an authors
 */
@RestController
@RequestMapping("books/{isbn}/authors")
@ApiVersion(1)
public class BooksAuthorsController {
    BooksAuthorsService booksAuthorsService;

    public BooksAuthorsController(BooksAuthorsService booksAuthorsService) {
        this.booksAuthorsService = booksAuthorsService;
    }

    @GetMapping("")
    @Cacheable("cache")
    public Page<Author> getAuthorsByBook(@QuerydslPredicate(root = Author.class) Predicate predicate,
            @PathVariable("isbn") String isdn, Pageable pageable) {
        return this.booksAuthorsService.findAllAuthorsForBook(isdn, pageable, predicate);
    }

    @PostMapping("{uuid}")
    public Book assignAuthorToBook(@PathVariable("uuid") UUID uuid,
            @PathVariable("isbn") String ISBN) {
        return this.booksAuthorsService.assignAuthorToBook(uuid, ISBN);
    }

    @DeleteMapping("{uuid}")
    public Book deleteAuthorFromBook(@PathVariable("uuid") UUID uuid,
            @PathVariable("isbn") String ISBN) {
        return this.booksAuthorsService.deleteAuthorFromBook(uuid, ISBN);
    }

}
