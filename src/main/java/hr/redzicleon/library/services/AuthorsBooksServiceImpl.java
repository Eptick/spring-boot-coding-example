package hr.redzicleon.library.services;

import java.util.UUID;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.stereotype.Service;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.AuthorBook;
import hr.redzicleon.library.repository.AuthorsBooksRepository;

@Service
public class AuthorsBooksServiceImpl implements AuthorsBooksService {
    private final AuthorsService authorsService;
    private final AuthorsBooksRepository authorsBooksRepository;

    public AuthorsBooksServiceImpl(AuthorsService authorsService, AuthorsBooksRepository authorsBooksRepository) {
        this.authorsService = authorsService;
        this.authorsBooksRepository = authorsBooksRepository;
    }

    public Author assignBookToAuthor(UUID uuid, @ISBN String isbn) {
        authorsBooksRepository.save(new AuthorBook(uuid, isbn));
        return this.authorsService.getAuthor(uuid);
    }
}
