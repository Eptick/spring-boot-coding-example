package hr.redzicleon.library.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hr.redzicleon.library.annotations.ISBN;
import hr.redzicleon.library.domain.audit.AuditableEntity;

@Entity
@Table(name = "books")
public class Book extends AuditableEntity {

    @Id
    @ISBN
    @Column(name = "ISBN", updatable = false, nullable = false)
    @NotBlank
    private String ISBN;

    @Column(name = "title", updatable = true, nullable = false)
    @NotBlank
    private String title;

    @Column(name = "genre", updatable = true, nullable = false)
    @NotBlank
    private String genre;

    @ManyToMany()
    @JsonIgnore
    @JoinTable(name = "authors_books",
        joinColumns = @JoinColumn(name = "book_isbn"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}