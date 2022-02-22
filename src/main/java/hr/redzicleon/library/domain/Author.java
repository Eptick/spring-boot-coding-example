package hr.redzicleon.library.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Formula;

import javax.persistence.JoinColumn;

import hr.redzicleon.library.domain.audit.AuditableEntity;


@Entity()
@Table(name = "authors")
public class Author extends AuditableEntity {

    @Id
    @Column(name = "author_id", updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID authorId;

    @Column(name = "name", updatable = true, nullable = false)
    private String name;

    @ManyToMany()
    @JsonIgnore
    @JoinTable(name = "authors_books",
        joinColumns = @JoinColumn(name = "author_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<>();

    @Formula("(SELECT COUNT(authors_books.author_id) FROM authors " +
         "left join authors_books on authors_books.author_id = authors.author_id " +
         "WHERE authors.author_id = author_id " +
         "GROUP BY authors.author_id)")
    private int bookCount;

    public UUID getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public int getBookCount() {
        return this.bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

}