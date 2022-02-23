package hr.redzicleon.library.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.redzicleon.library.domain.Author;
import hr.redzicleon.library.repository.AuthorsRepository;

@RestController()
@RequestMapping("authors")
public class AuthorsController {
    @Autowired
    AuthorsRepository authorsRepository;

    @GetMapping("")
    public Page<Author> index(
        @PageableDefault(
            sort = "createdAt",
            direction = Sort.Direction.DESC
        ) Pageable pageable) {
        return authorsRepository.findAll(pageable);
    }

    @PostMapping("")
    public Author createAuthor() {
        Author author = new Author();
        author.setName(UUID.randomUUID().toString());
        return authorsRepository.save(author);
    }
}
