package hr.redzicleon.library.services;

import java.util.Set;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hr.redzicleon.library.domain.Book;
import hr.redzicleon.library.domain.dto.book.BookDto;
import hr.redzicleon.library.domain.dto.book.CreateBookDto;

public interface BooksService {
    public Book getBook(String isbn);
    public Page<Book> getBooks(Predicate predicate, Pageable pageable);
    public Book updateBook(String isbn, BookDto dto);
    public Book saveBook(Book book);
    public Book saveBook(CreateBookDto dto);
    public Iterable<Book> updateOrCreateBooks(Set<BookDto> dto);
    public void deleteBook(String isbn);
}
