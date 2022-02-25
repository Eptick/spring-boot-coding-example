package hr.redzicleon.library.services;

import java.util.Set;
import java.util.UUID;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.dto.author.AuthorDto;
import hr.redzicleon.library.domain.dto.author.CreateAuthorDto;
import hr.redzicleon.library.domain.dto.author.UpdateAuthorDto;

public interface AuthorsService {
    public Page<Author> getAuthors(Predicate predicate, Pageable pageable);
    public Author getAuthor(UUID uuid);
    public Author updateAuthor(UUID uuid, UpdateAuthorDto author);
    public Author saveAuthor(Author author);
    public Author saveAuthor(CreateAuthorDto author);
    public Iterable<Author> updateOrCreateAuthors(Set<AuthorDto> author);
    public void deleteAuthor(UUID uuid);
}
