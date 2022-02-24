package hr.redzicleon.library.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;

import hr.redzicleon.library.domain.Book;
import hr.redzicleon.library.domain.QBook;

public interface BooksRepository extends PagingAndSortingRepository<Book, String>, QuerydslPredicateExecutor<Book>, QuerydslBinderCustomizer<QBook> {
    @Override
    default void customize(QuerydslBindings bindings, QBook book) {
        bindings.bind(book.authors.any().name).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
