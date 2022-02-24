package hr.redzicleon.library.services;

import java.util.UUID;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.dto.CreateAuthorDto;

public interface AuthorsService {
    public Page<Author> getAuthors(Predicate predicate, Pageable pageable);
    public Author getAuthor(UUID uuid);
    public Author saveAuthor(CreateAuthorDto author);
    public void deleteAuthor(UUID uuid);
}
