package hr.redzicleon.library.services;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.dto.CreateAuthorDto;

public interface AuthorsService {
    public Page<Author> getAuthors(Predicate predicate, Pageable pageable);
    public Author saveAuthor(CreateAuthorDto author);
}
