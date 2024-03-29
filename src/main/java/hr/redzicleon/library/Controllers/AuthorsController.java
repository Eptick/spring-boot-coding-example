package hr.redzicleon.library.controllers;

import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.querydsl.core.types.Predicate;

import org.springframework.cache.annotation.Cacheable;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.redzicleon.library.annotations.ApiVersion;
import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.dto.author.AuthorDto;
import hr.redzicleon.library.domain.dto.author.CreateAuthorDto;
import hr.redzicleon.library.domain.dto.author.UpdateAuthorDto;
import hr.redzicleon.library.services.AuthorsService;

/**
 * /v1/authors/**
 * 
 * Operations on modifiying the authors
 */
@RestController()
@Validated
@RequestMapping("authors")
@ApiVersion(1)
public class AuthorsController {
    private final AuthorsService authorsService;

    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping("")
    @Cacheable("cache")
    public Page<Author> index(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @QuerydslPredicate Predicate predicate) {
        return this.authorsService.getAuthors(predicate, pageable);
    }

    @GetMapping("{uuid}")
    @Cacheable("cache")
    public Author getAuthor(@PathVariable("uuid") UUID uuid) {
        return this.authorsService.getAuthor(uuid);
    }

    @PostMapping("")
    public Author createAuthor(@Valid @RequestBody CreateAuthorDto dto) {
        return this.authorsService.saveAuthor(dto);
    }

    @PutMapping("{uuid}")
    @Cacheable("cache")
    public Author updateAuthor(@PathVariable("uuid") UUID uuid, @Valid @RequestBody UpdateAuthorDto dto) {
        return this.authorsService.updateAuthor(uuid, dto);
    }

    @PatchMapping("")
    public Iterable<Author> updateOrCreateAuthors(@RequestBody @NotNull Set<AuthorDto> dto) {
        return this.authorsService.updateOrCreateAuthors(dto);
    }

    @DeleteMapping("{uuid}")
    public void deleteAuthor(@PathVariable("uuid") UUID uuid) {
        this.authorsService.deleteAuthor(uuid);
    }
}
