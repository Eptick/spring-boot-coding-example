package hr.redzicleon.library.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.Book;
import hr.redzicleon.library.repository.AuthorsRepository;
import hr.redzicleon.library.repository.BooksRepository;

@RestController()
@RequestMapping("books")
public class BooksController {
    @Autowired
    BooksRepository booksRepository;
    @Autowired
    AuthorsRepository authorsRepository;

    @GetMapping("")
    public Page<Book> index(@PageableDefault(
        sort = "createdAt",
        direction = Sort.Direction.DESC)
        Pageable pageable) {
        return booksRepository.findAll(pageable);
    }

    @PostMapping("")
    public Book createBook() {
        Book book = new Book();
        book.setTitle(UUID.randomUUID().toString());
        book.setGenre(UUID.randomUUID().toString());
        book.setISBN("99921-58-10-7");
        book = booksRepository.save(book);
        Author author = authorsRepository.findAll(PageRequest.of(0, 5)).getContent().get(0);
        var list = author.getBooks();
        list.add(book);
        author.setBooks(list);
        authorsRepository.save(author);
        return book;
    }
}
