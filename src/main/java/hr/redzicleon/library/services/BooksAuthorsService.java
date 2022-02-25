package hr.redzicleon.library.services;

import java.util.UUID;

import com.querydsl.core.types.Predicate;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.Book;

public interface BooksAuthorsService {
    public Page<Author> findAllAuthorsForBook(String isbn, Pageable pageable, Predicate predicate);
    public Book assignAuthorToBook(UUID uuid, @ISBN String isbn);
    public Book deleteAuthorFromBook(UUID uuid, @ISBN String isbn);
}
