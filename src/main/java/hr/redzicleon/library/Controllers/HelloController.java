package hr.redzicleon.library.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.redzicleon.library.repository.AuthorsRepository;

@RestController
public class HelloController {

    @Autowired
    AuthorsRepository authorsRepository;

    @GetMapping()
    public String index() {
        var author = authorsRepository.findById(UUID.randomUUID());
        if(author.isPresent()) {
            return author.get().getName();
        } else {
            return "Hello World";
        }
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin";
    }

    @GetMapping("/publisher")
    public String publisher() {
        return "Hello publisher";
    }
}
