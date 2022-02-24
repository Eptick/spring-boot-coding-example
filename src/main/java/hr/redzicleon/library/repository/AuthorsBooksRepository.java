package hr.redzicleon.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.redzicleon.library.domain.AuthorBook;
import hr.redzicleon.library.domain.AuthorBookId;

public interface AuthorsBooksRepository extends JpaRepository<AuthorBook, AuthorBookId> {
    
}
