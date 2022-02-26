package hr.redzicleon.library.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;

import com.querydsl.core.types.Predicate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.dto.author.AuthorDto;
import hr.redzicleon.library.domain.dto.author.CreateAuthorDto;
import hr.redzicleon.library.domain.dto.author.UpdateAuthorDto;
import hr.redzicleon.library.repository.AuthorsRepository;

@Service
@Transactional
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

    public Author getAuthor(UUID uuid) {
        Optional<Author> author = this.authorsRepository.findById(uuid);
        if (author.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return author.get();
    }

    public Author saveAuthor(CreateAuthorDto dto) {
        return saveAuthor(this.mapper.map(dto, Author.class));
    }
    public Author saveAuthor(Author author) {
        return this.authorsRepository.save(author);
    }

    public Author updateAuthor(UUID uuid, UpdateAuthorDto dto) {
        Author author = this.getAuthor(uuid);
        BeanUtils.copyProperties(dto, author);
        return this.authorsRepository.save(author);
    }

    /**
     * Splits the set into 2 groups, new creations and updates
     * then updates the existing items, after which it creates the new items
     * Then returns it as one set
     */
    public Iterable<Author> updateOrCreateAuthors(Set<AuthorDto> dto) {

        Map<Boolean, List<AuthorDto>> groups = (StreamSupport.stream(dto.spliterator(), false)
                .collect(Collectors.partitioningBy(elem -> elem.getId() != null)));

        Map<UUID, AuthorDto> updatableUUIDs = (StreamSupport.stream(groups.get(true).spliterator(), false))
                .collect(Collectors.toMap(x -> x.getId(), x -> x));

        Iterable<Author> updated = this.authorsRepository.saveAll(
                StreamSupport.stream(this.authorsRepository.findAllById(updatableUUIDs.keySet()).spliterator(), false)
                        .map(elem -> {
                            AuthorDto newAuthor = updatableUUIDs.get(elem.getId());
                            BeanUtils.copyProperties(newAuthor, elem, "id");
                            return elem;
                        }).collect(Collectors.toSet()));
        // We could add a check here to warn against non existing uuids instead
        // of failing silently

        Iterable<Author> created = this.authorsRepository.saveAll(
            StreamSupport.stream(groups.get(false).spliterator(), false)
                            .map(elem -> mapper.map(elem, Author.class)).collect(Collectors.toSet())
        );

        return Stream.concat(
                StreamSupport.stream(updated.spliterator(), false),
                StreamSupport.stream(created.spliterator(), false)).collect(Collectors.toSet());
    }

    public void deleteAuthor(UUID uuid) {
        this.authorsRepository.deleteById(uuid);
    }
}
