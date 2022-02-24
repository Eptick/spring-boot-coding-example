package hr.redzicleon.library.services;

import java.util.UUID;

import org.hibernate.validator.constraints.ISBN;

import hr.redzicleon.library.domain.Author;

public interface AuthorsBooksService {
    public Author assignBookToAuthor(UUID uuid, @ISBN String isbn);
}
