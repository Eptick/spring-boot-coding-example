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
import hr.redzicleon.library.domain.QAuthor;
import hr.redzicleon.library.repository.AuthorsBooksRepository;
import hr.redzicleon.library.repository.AuthorsRepository;

@Service
public class BooksAuthorsServiceImpl implements BooksAuthorsService {
    private final BooksService booksService;
    private final AuthorsRepository authorsRepository;
    private final AuthorsBooksRepository authorsBooksRepository;

    public BooksAuthorsServiceImpl(
        BooksService booksService,
        AuthorsRepository authorsRepository,
        AuthorsBooksRepository authorsBooksRepository) {
        this.booksService = booksService;
        this.authorsRepository = authorsRepository;
        this.authorsBooksRepository = authorsBooksRepository;
    }

    public Book assignAuthorToBook(UUID uuid, @ISBN String isbn) {
        authorsBooksRepository.save(new AuthorBook(uuid, isbn));
        return this.booksService.getBook(isbn);
    }

    public Book deleteAuthorFromBook(UUID uuid, @ISBN String isbn) {
        authorsBooksRepository.deleteById(new AuthorBookId(uuid, isbn));
        return this.booksService.getBook(isbn);
    }

    public Page<Author> findAllAuthorsForBook(String isbn, Pageable pageable, Predicate predicate) {
        BooleanBuilder booleanBuilder = new BooleanBuilder(predicate);
        booleanBuilder.and(QAuthor.author.books.any().ISBN.eq(isbn));
        return this.authorsRepository.findAll(booleanBuilder, pageable);
    }
}
