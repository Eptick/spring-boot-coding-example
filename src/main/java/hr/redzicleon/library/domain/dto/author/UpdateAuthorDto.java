package hr.redzicleon.library.domain.dto.author;

import javax.validation.constraints.NotBlank;

public class UpdateAuthorDto {
    @NotBlank
    public String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
