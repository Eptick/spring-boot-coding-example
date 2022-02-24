package hr.redzicleon.library.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AuthorBookId implements Serializable {
    @Column(name = "author_id")
    private UUID authorId;
    @Column(name = "book_isbn")
    private String bookIsbn;

    public AuthorBookId() {
    }

    public AuthorBookId(UUID uuid, String isbn) {
        this.authorId = uuid;
        this.bookIsbn = isbn;
    }

    public UUID getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public String getBookIsbn() {
        return this.bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AuthorBookId)) {
            return false;
        }
        AuthorBookId authorBookKey = (AuthorBookId) o;
        return Objects.equals(authorId, authorBookKey.authorId) && Objects.equals(bookIsbn, authorBookKey.bookIsbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, bookIsbn);
    }

}
