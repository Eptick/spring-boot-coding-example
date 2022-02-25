package hr.redzicleon.library.domain;

import java.util.UUID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "authors_books")
public class AuthorBook {
    @EmbeddedId
    private AuthorBookId bookId;

    public AuthorBook() {
    }

    public AuthorBook(UUID uuid, String isbn) {
        this.bookId = new AuthorBookId(uuid, isbn);
    }

    public AuthorBookId getBookId() {
        return this.bookId;
    }

    public void setBookId(AuthorBookId bookId) {
        this.bookId = bookId;
    }

    public AuthorBookId getId() {
        return this.bookId;
    }
}
