package hr.redzicleon.library.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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

import hr.redzicleon.library.domain.Book;
import hr.redzicleon.library.domain.dto.book.BookDto;
import hr.redzicleon.library.domain.dto.book.UpdateBookDto;
import hr.redzicleon.library.repository.BooksRepository;

@Service
@Transactional
public class BooksServiceImpl implements BooksService {

    private final BooksRepository booksRepository;
    private final ModelMapper mapper;

    public BooksServiceImpl(
            ModelMapper mapper,
            BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
        this.mapper = mapper;
    }

    public Page<Book> getBooks(Predicate predicate, Pageable pageable) {
        return this.booksRepository.findAll(predicate, pageable);
    }

    public Book getBook(String isbn) {
        Optional<Book> book = this.booksRepository.findById(isbn);
        if (book.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return book.get();
    }

    public Book saveBook(BookDto dto) {
        return saveBook(mapper.map(dto, Book.class));
    }

    public Book saveBook(Book book) {
        return this.booksRepository.save(book);
    }

    public Book updateBook(String isbn, UpdateBookDto dto) {
        Book book = this.getBook(isbn);
        BeanUtils.copyProperties(dto, book);
        return this.booksRepository.save(book);
    }

    public Iterable<Book> updateOrCreateBooks(Set<BookDto> dto) {

        Map<Boolean, List<BookDto>> groups = (StreamSupport.stream(dto.spliterator(), false)
                .collect(Collectors.partitioningBy(elem -> elem.getISBN() != null)));

        Map<String, BookDto> updatableUUIDs = (StreamSupport.stream(groups.get(true).spliterator(), false))
                .collect(Collectors.toMap(x -> x.getISBN(), x -> x));

        Iterable<Book> updated = this.booksRepository.saveAll(
                StreamSupport.stream(this.booksRepository.findAllById(updatableUUIDs.keySet()).spliterator(), false)
                        .map(elem -> {
                            BookDto newBook = updatableUUIDs.get(elem.getISBN());
                            BeanUtils.copyProperties(newBook, elem, "ISBN");
                            return elem;
                        }).collect(Collectors.toSet()));
        // We could add a check here to warn against non existing uuids instead
        // of failing silently

        Iterable<Book> created = this.booksRepository.saveAll(
            StreamSupport.stream(groups.get(false).spliterator(), false)
                            .map(elem -> mapper.map(elem, Book.class)).collect(Collectors.toSet())
        );

        return Stream.concat(
                StreamSupport.stream(updated.spliterator(), false),
                StreamSupport.stream(created.spliterator(), false)).collect(Collectors.toSet());
    }

    public void deleteBook(String isbn) {
        this.booksRepository.deleteById(isbn);
    }
}
