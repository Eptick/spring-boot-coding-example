package hr.redzicleon.library.domain;

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
@SQLInsert(sql = "insert into books (created_at, updated_at, genre, title, isbn) values (?, ?, ?, ?, isbn13(?))")
public class Book extends AuditableEntity {

    @Id
    @ISBN
    @Column(name = "ISBN", updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String ISBN;

    @Column(name = "title", updatable = true, nullable = false)
    private String title;

    @Column(name = "genre", updatable = true, nullable = false)
    private String genre;


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