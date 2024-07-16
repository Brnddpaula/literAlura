package challenge.alura.literAlura.service;

import challenge.alura.literAlura.model.Autor;
import challenge.alura.literAlura.model.Book;
import challenge.alura.literAlura.repository.AutorRepository;
import challenge.alura.literAlura.repository.BookRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public GutendexService(RestTemplate restTemplate, BookRepository bookRepository, AutorRepository autorRepository) {
        this.restTemplate = restTemplate;
        this.bookRepository = bookRepository;
        this.autorRepository = autorRepository;
    }

    public void fetchBooks(int year, String languages) {
        String url = "https://gutendex.com/books?languages=" + languages + "&search=" + year;
        String response = restTemplate.getForObject(url, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            List<Book> books = new ArrayList<>();
            for (JsonNode bookNode : root.path("results")) {
                Book book = new Book();
                book.setTitle(bookNode.path("title").asText());
                book.setLanguages(bookNode.path("languages").asText());
                book.setDownloads(bookNode.path("download_count").asInt());

                JsonNode authorNode = bookNode.path("authors").get(0);
                if (authorNode != null) {
                    Autor autor = new Autor();
                    autor.setName(authorNode.path("name").asText());
                    autor.setBirthYear(authorNode.path("birth_year").asInt());
                    autor.setDeathYear(authorNode.path("death_year").asInt());
                    autorRepository.save(autor);
                    book.setAuthor(autor);
                }
                books.add(book);
            }
            bookRepository.saveAll(books);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao obter livros ou nenhum livro encontrado.");
        }
    }
}
