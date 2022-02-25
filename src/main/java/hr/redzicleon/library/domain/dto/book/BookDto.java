package hr.redzicleon.library.domain.dto.book;

import javax.validation.constraints.NotBlank;

import hr.redzicleon.library.annotations.ISBN;

public class BookDto extends UpdateBookDto {
    @ISBN
    @NotBlank
    private String ISBN;

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

}
