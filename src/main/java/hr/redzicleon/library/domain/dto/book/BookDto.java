package hr.redzicleon.library.domain.dto.book;

import javax.validation.constraints.NotBlank;

import hr.redzicleon.library.annotations.ISBN;

public class BookDto extends UpdateBookDto {
    @ISBN
    @NotBlank
    private String ISBN;
}
