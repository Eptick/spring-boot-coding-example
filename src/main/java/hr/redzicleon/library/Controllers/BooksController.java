package hr.redzicleon.library.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.redzicleon.library.domain.Book;
import hr.redzicleon.library.repository.BooksRepository;

@RestController()
@RequestMapping("books")
public class BooksController {
    @Autowired
    BooksRepository booksRepository;

    @GetMapping("")
    public Page<Book> index(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }

    @PostMapping("")
    public Book createBook() {
        Book book = new Book();
        book.setTitle(UUID.randomUUID().toString());
        book.setGenre(UUID.randomUUID().toString());
        book.setISBN("99921-58-10-7");
        return booksRepository.save(book);
    }
}
