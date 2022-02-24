package hr.redzicleon.library.controllers;

import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.dto.AuthorDto;
import hr.redzicleon.library.domain.dto.CreateAuthorDto;
import hr.redzicleon.library.services.AuthorsService;

@RestController()
@Validated
@RequestMapping("authors")
public class AuthorsController {
    private final AuthorsService authorsService;

    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping("")
    public Page<Author> index(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @QuerydslPredicate Predicate predicate) {
        return this.authorsService.getAuthors(predicate, pageable);
    }

    @GetMapping("{uuid}")
    public Author getAuthor(@PathVariable("uuid") UUID uuid) {
        return this.authorsService.getAuthor(uuid);
    }

    @PostMapping("")
    public Author createAuthor(@Valid @RequestBody CreateAuthorDto dto) {
        return this.authorsService.saveAuthor(dto);
    }

    @PatchMapping("")
    public Iterable<Author> updateOrCreateAuthors(@RequestBody @NotNull Set<@Valid AuthorDto> dto) {
        return this.authorsService.updateOrCreateAuthors(dto);
    }

    @DeleteMapping("{uuid}")
    public void deleteAuthor(@PathVariable("uuid") UUID uuid) {
        this.authorsService.deleteAuthor(uuid);
    }
}
