package hr.redzicleon.library.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import hr.redzicleon.library.domain.Book;

public interface BooksRepository extends PagingAndSortingRepository<Book, UUID> {
    Optional<Book> findById(UUID id);
}
