package hr.redzicleon.library.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.querydsl.core.types.Predicate;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.redzicleon.library.domain.Book;
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

    // public Author saveAuthor(CreateAuthorDto dto) {
    //     return this.authorsRepository.save(this.mapper.map(dto, Author.class));
    // }

    // public Author updateAuthor(UUID uuid, UpdateAuthorDto dto) {
    //     Author author = this.getAuthor(uuid);
    //     BeanUtils.copyProperties(dto, author);
    //     return this.authorsRepository.save(author);
    // }

    // public Iterable<Author> updateOrCreateAuthors(Set<AuthorDto> dto) {

    //     Map<Boolean, List<AuthorDto>> groups = (StreamSupport.stream(dto.spliterator(), false)
    //             .collect(Collectors.partitioningBy(elem -> elem.getId() != null)));

    //     Map<UUID, AuthorDto> updatableUUIDs = (StreamSupport.stream(groups.get(true).spliterator(), false))
    //             .collect(Collectors.toMap(x -> x.getId(), x -> x));

    //     Iterable<Author> updated = this.authorsRepository.saveAll(
    //             StreamSupport.stream(this.authorsRepository.findAllById(updatableUUIDs.keySet()).spliterator(), false)
    //                     .map(elem -> {
    //                         AuthorDto newAuthor = updatableUUIDs.get(elem.getId());
    //                         BeanUtils.copyProperties(newAuthor, elem, "id");
    //                         return elem;
    //                     }).collect(Collectors.toSet()));
    //     // We could add a check here to warn against non existing uuids instead
    //     // of failing silently

    //     Iterable<Author> created = this.authorsRepository.saveAll(
    //         StreamSupport.stream(groups.get(false).spliterator(), false)
    //                         .map(elem -> mapper.map(elem, Author.class)).collect(Collectors.toSet())
    //     );

    //     return Stream.concat(
    //             StreamSupport.stream(updated.spliterator(), false),
    //             StreamSupport.stream(created.spliterator(), false)).collect(Collectors.toSet());
    // }

    // public void deleteAuthor(UUID uuid) {
    //     this.authorsRepository.deleteById(uuid);
    // }
}
