package hr.redzicleon.library.services;

import java.util.UUID;

import com.querydsl.core.types.Predicate;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.Book;

public interface AuthorsBooksService {
    public Page<Book> findAllBooksByAuthor(UUID uuid, Pageable pageable, Predicate predicate);
    public Author assignBookToAuthor(UUID uuid, @ISBN String isbn);
}
