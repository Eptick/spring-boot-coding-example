package hr.redzicleon.library.repository;


import java.util.Optional;
import java.util.UUID;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.QAuthor;

public interface AuthorsRepository extends PagingAndSortingRepository<Author, UUID>, QuerydslPredicateExecutor<Author>, QuerydslBinderCustomizer<QAuthor> {
    Optional<Author> findById(UUID id);
    default void customize(QuerydslBindings bindings, QAuthor author) {
        bindings.bind(author.books.any().title).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
