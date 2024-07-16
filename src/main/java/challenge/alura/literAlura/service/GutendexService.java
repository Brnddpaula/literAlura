package challenge.alura.literAlura.service;

import challenge.alura.literAlura.model.GutendexResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class GutendexService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GutendexService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Optional<GutendexResponse> fetchBooks(String authorYearStart, String languages) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://gutendex.com/books?author_year_start=" + authorYearStart + "&languages=" + languages))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                GutendexResponse gutendexResponse = objectMapper.readValue(response.body(), GutendexResponse.class);
                return Optional.of(gutendexResponse);
            } else {
                System.out.println("Erro na solicitação: " + response.statusCode());
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void printBooks(GutendexResponse response) {
        if (response != null && response.getResults() != null) {
            response.getResults().forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro encontrado.");
        }
    }
}