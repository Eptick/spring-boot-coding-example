package hr.redzicleon.library.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import hr.redzicleon.library.domain.AuthorBook;
import hr.redzicleon.library.domain.AuthorBookId;

public interface AuthorsBooksRepository extends PagingAndSortingRepository<AuthorBook, AuthorBookId> {
}
