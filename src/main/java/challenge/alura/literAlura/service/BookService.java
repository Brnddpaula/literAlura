package challenge.alura.literAlura.service;

import challenge.alura.literAlura.model.Autor;
import challenge.alura.literAlura.model.Book;
import challenge.alura.literAlura.repository.AutorRepository;
import challenge.alura.literAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AutorRepository autorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public void insertData(Scanner scanner) {
        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();
        System.out.print("Digite os idiomas do livro (separados por vírgulas): ");
        String languages = scanner.nextLine();
        System.out.print("Digite o número de downloads: ");
        int downloads = scanner.nextInt();
        scanner.nextLine(); // Consuming the newline left-over

        Book book = new Book();
        book.setTitle(title);
        book.setLanguages(languages);
        book.setDownloads(downloads);

        saveBook(book);
        System.out.println("Livro inserido com sucesso!");
    }

    public void listAuthors() {
        List<Autor> authors = autorRepository.findAll();
        for (Autor author : authors) {
            System.out.println("Nome: " + author.getName());
            System.out.println("Ano de Nascimento: " + author.getBirthYear());
            System.out.println("Ano de Falecimento: " + (author.getDeathYear() != null ? author.getDeathYear() : "Ainda vivo"));
            System.out.println();
        }
    }

    public void listLivingAuthors(int year) {
        List<Autor> authors = autorRepository.findAuthorsAliveInYear(year);
        for (Autor author : authors) {
            System.out.println("Nome: " + author.getName());
            System.out.println("Ano de Nascimento: " + author.getBirthYear());
            System.out.println("Ano de Falecimento: " + (author.getDeathYear() != null ? author.getDeathYear() : "Ainda vivo"));
            System.out.println();
        }
    }
}
