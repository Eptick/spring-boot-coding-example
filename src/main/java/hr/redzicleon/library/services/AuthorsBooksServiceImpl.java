package hr.redzicleon.library.services;

import java.util.UUID;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.AuthorBook;
import hr.redzicleon.library.domain.AuthorBookId;
import hr.redzicleon.library.domain.Book;
import hr.redzicleon.library.domain.QBook;
import hr.redzicleon.library.repository.AuthorsBooksRepository;
import hr.redzicleon.library.repository.BooksRepository;

@Service
public class AuthorsBooksServiceImpl implements AuthorsBooksService {
    private final AuthorsService authorsService;
    private final AuthorsBooksRepository authorsBooksRepository;
    private final BooksRepository booksRepository;

    public AuthorsBooksServiceImpl(AuthorsService authorsService, AuthorsBooksRepository authorsBooksRepository, BooksRepository booksRepository) {
        this.authorsService = authorsService;
        this.authorsBooksRepository = authorsBooksRepository;
        this.booksRepository = booksRepository;
    }

    public Author assignBookToAuthor(UUID uuid, @ISBN String isbn) {
        authorsBooksRepository.save(new AuthorBook(uuid, isbn));
        return this.authorsService.getAuthor(uuid);
    }

    public Author deleteBookFromAuthor(UUID uuid, @ISBN String isbn) {
        authorsBooksRepository.deleteById(new AuthorBookId(uuid, isbn));
        return this.authorsService.getAuthor(uuid);
    }

    public Page<Book> findAllBooksByAuthor(UUID uuid, Pageable pageable, Predicate predicate) {
        BooleanBuilder booleanBuilder = new BooleanBuilder(predicate);
        booleanBuilder.and(QBook.book.authors.any().id.eq(uuid));
        return this.booksRepository.findAll(booleanBuilder, pageable);
    }
}
