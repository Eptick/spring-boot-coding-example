package hr.redzicleon.library.controllers;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.domain.dto.CreateAuthorDto;
import hr.redzicleon.library.services.AuthorsService;

@RestController()
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

    @PostMapping("")
    public Author createAuthor(@Valid @RequestBody CreateAuthorDto dto) {
        return this.authorsService.saveAuthor(dto);
    }
}
