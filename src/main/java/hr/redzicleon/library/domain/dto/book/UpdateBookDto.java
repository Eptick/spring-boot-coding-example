package hr.redzicleon.library.domain.dto.book;

import javax.validation.constraints.NotBlank;

public class UpdateBookDto {
    @NotBlank
    private String title;
    @NotBlank
    private String genre;
}
