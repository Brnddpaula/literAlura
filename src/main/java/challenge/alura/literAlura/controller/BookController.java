package challenge.alura.literAlura.controller;

import challenge.alura.literAlura.model.GutendexResponse;
import challenge.alura.literAlura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private GutendexService gutendexService;

    @GetMapping("/books")
    public String getBooks(@RequestParam String authorYearStart, @RequestParam String languages) {
        Optional<GutendexResponse> books = gutendexService.fetchBooks(authorYearStart, languages);
        books.ifPresent(gutendexService::printBooks);
        return books.map(GutendexResponse::toString).orElse("Não foi possível obter os livros.");
    }
}