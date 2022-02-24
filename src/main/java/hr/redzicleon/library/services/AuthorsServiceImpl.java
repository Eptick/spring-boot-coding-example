package hr.redzicleon.library.services;

import com.querydsl.core.types.Predicate;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.dto.CreateAuthorDto;
import hr.redzicleon.library.repository.AuthorsRepository;

@Service
public class AuthorsServiceImpl implements AuthorsService {

    private final AuthorsRepository authorsRepository;
    private final ModelMapper mapper;

    public AuthorsServiceImpl(
            ModelMapper mapper,
            AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
        this.mapper = mapper;
    }

    public Page<Author> getAuthors(Predicate predicate, Pageable pageable) {
        return this.authorsRepository.findAll(predicate, pageable);
    }

    public Author saveAuthor(CreateAuthorDto author) {
        return this.authorsRepository.save(this.mapper.map(author, Author.class));
    }
}
