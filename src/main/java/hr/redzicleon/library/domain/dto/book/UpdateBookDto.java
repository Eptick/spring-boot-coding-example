package hr.redzicleon.library.domain.dto.book;

import javax.validation.constraints.NotBlank;

public class UpdateBookDto {
    @NotBlank
    private String title;
    @NotBlank
    private String genre;

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
