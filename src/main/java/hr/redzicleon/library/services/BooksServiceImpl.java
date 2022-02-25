package hr.redzicleon.library.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import com.querydsl.core.types.Predicate;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.redzicleon.library.BeanUtils;
import hr.redzicleon.library.domain.Book;
import hr.redzicleon.library.domain.dto.book.BookDto;
import hr.redzicleon.library.domain.dto.book.CreateBookDto;
import hr.redzicleon.library.repository.BooksRepository;

@Service
@Transactional
public class BooksServiceImpl implements BooksService {

    private final BooksRepository booksRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    public BooksServiceImpl(
            Validator validator,
            ModelMapper mapper,
            BooksRepository booksRepository) {
        this.validator = validator;
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

    public Book saveBook(CreateBookDto dto) {
        return saveBook(mapper.map(dto, Book.class));
    }

    public Book saveBook(Book book) {
        return this.booksRepository.save(book);
    }

    public Book updateBook(String isbn, BookDto dto) {
        Book book = this.getBook(isbn);
        BeanUtils.copyProperties(dto, book);
        return this.booksRepository.save(book);
    }

    public Iterable<Book> updateOrCreateBooks(Set<BookDto> dto) {

        Set<String> keys = dto.stream().map(x -> x.getISBN()).collect(Collectors.toSet());

        Map<String, Book> existingItems = StreamSupport
                .stream(this.booksRepository.findAllById(keys).spliterator(), false)
                .collect(Collectors.toMap(x -> x.getISBN(), x -> x));

        Map<Boolean, Map<String, BookDto>> groups = dto.stream()
                .collect(Collectors.partitioningBy(
                        e -> existingItems.containsKey(e.getISBN()),
                        Collectors.toMap(
                                x -> x.getISBN(),
                                x -> x)));

        Iterable<Book> updated = this.booksRepository.saveAll(
                existingItems.values().stream()
                        .map(elem -> {
                            BookDto newBook = groups.get(true).get(elem.getISBN());
                            BeanUtils.copyProperties(newBook, elem, "ISBN");
                            return elem;
                        }).collect(Collectors.toSet()));

        Iterable<@Valid Book> toCreate = StreamSupport.stream(groups.get(false).values().spliterator(), false)
                .map(elem -> mapper.map(elem, Book.class))
                .collect(Collectors.toSet());
        Iterable<Book> created = this.booksRepository.saveAll(toCreate);

        return Stream.concat(
                StreamSupport.stream(updated.spliterator(), false),
                StreamSupport.stream(created.spliterator(), false)).collect(Collectors.toSet());
    }

    public void deleteBook(String isbn) {
        this.booksRepository.deleteById(isbn);
    }
}
