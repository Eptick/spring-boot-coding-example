package hr.redzicleon.library.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLInsert;

import hr.redzicleon.library.annotations.ISBN;
import hr.redzicleon.library.domain.audit.AuditableEntity;

@Entity
@Table(name = "books")
@SQLInsert(sql = "insert into books (created_at, updated_at, isbn, genre, title, book_id) values (?, ?, isbn13(?), ?, ?, ?)")
public class Book extends AuditableEntity {

    @Id
    @Column(name = "book_id", updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID bookId;

    @Column(name = "title", updatable = true, nullable = false)
    private String title;

    @ISBN
    @Column(name = "ISBN", updatable = true, nullable = false)
    private String ISBN;

    @Column(name = "genre", updatable = true, nullable = false)
    private String genre;

    public UUID getBookId() {
        return this.bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

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