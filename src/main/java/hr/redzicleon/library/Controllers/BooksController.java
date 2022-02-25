package hr.redzicleon.library.controllers;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.redzicleon.library.domain.Book;
import hr.redzicleon.library.domain.dto.book.BookDto;
import hr.redzicleon.library.domain.dto.book.CreateBookDto;
import hr.redzicleon.library.services.BooksService;

@Validated
@RestController
@RequestMapping("books")
public class BooksController {

    BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("")
    public Page<Book> index(@PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @QuerydslPredicate Predicate predicate) {
        return this.booksService.getBooks(predicate, pageable);
    }

    @GetMapping("{isbn}")
    public Book getBook(@PathVariable("isbn") String isbn) {
        return this.booksService.getBook(isbn);
    }

    @PostMapping("")
    public Book createBook(@Valid @RequestBody CreateBookDto dto) {
        return this.booksService.saveBook(dto);
    }

    @PutMapping("{isbn}")
    public Book updateBook(@PathVariable("isbn") String isbn, @Valid @RequestBody BookDto dto) {
        return this.booksService.updateBook(isbn, dto);
    }

    @PatchMapping("")
    public Iterable<Book> updateOrCreateBooks(@RequestBody @NotNull Set<@Valid BookDto> dto) {
        return this.booksService.updateOrCreateBooks(dto);
    }

    @DeleteMapping("{isbn}")
    public void deleteBook(@PathVariable("isbn") String isbn) {
        this.booksService.deleteBook(isbn);
    }
}
