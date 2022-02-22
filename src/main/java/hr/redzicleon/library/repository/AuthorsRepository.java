package hr.redzicleon.library.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import hr.redzicleon.library.domain.Author;

public interface AuthorsRepository extends PagingAndSortingRepository<Author, UUID> {
    Optional<Author> findById(UUID id);
}
