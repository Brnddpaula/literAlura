package challenge.alura.literAlura;

import challenge.alura.literAlura.service.BookService;
import challenge.alura.literAlura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;

	@Autowired
	private GutendexService gutendexService;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Menu:");
			System.out.println("1. Inserir dados");
			System.out.println("2. Consultar livros");
			System.out.println("3. Listar autores");
			System.out.println("4. Listar autores vivos em determinado ano");
			System.out.println("5. Sair");
			System.out.print("Selecione uma opção: ");
			int option = scanner.nextInt();
			scanner.nextLine(); // Consuming the newline left-over

			switch (option) {
				case 1:
					bookService.insertData(scanner);
					break;
				case 2:
					System.out.print("Digite o ano de início do autor: ");
					int year = scanner.nextInt();
					scanner.nextLine(); // Consuming the newline left-over
					System.out.print("Digite os idiomas (separados por vírgulas, ex: en,fr): ");
					String languages = scanner.nextLine();
					gutendexService.fetchBooks(year, languages);
					break;
				case 3:
					bookService.listAuthors();
					break;
				case 4:
					System.out.print("Digite o ano: ");
					int aliveYear = scanner.nextInt();
					bookService.listLivingAuthors(aliveYear);
					break;
				case 5:
					return;
				default:
					System.out.println("Opção inválida!");
			}
		}
	}
}
