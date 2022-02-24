package hr.redzicleon.library.domain.dto;

import javax.validation.constraints.NotBlank;

public class CreateAuthorDto {
    @NotBlank
    public String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
