package hr.redzicleon.library.domain.dto.book;

import javax.validation.constraints.NotBlank;

import hr.redzicleon.library.annotations.ISBN;

public class BookDto {
    @ISBN
    @NotBlank
    private String ISBN;
    private String title;
    private String genre;

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}
