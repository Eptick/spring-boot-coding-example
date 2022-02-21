package hr.redzicleon.library.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping()
    public String index() {
        return "Hello World";
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
