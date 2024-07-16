package challenge.alura.literAlura.service;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class GutendexService {

    private final HttpClient httpClient;

    public GutendexService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public Optional<String> fetchBooks(String authorYearStart, String languages) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://gutendex.com/books?author_year_start=" + authorYearStart + "&languages=" + languages))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return Optional.of(response.body());
            } else {
                System.out.println("Erro na solicitação: " + response.statusCode());
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}