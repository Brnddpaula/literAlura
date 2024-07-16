// src/main/java/challenge/alura/literAlura/LiterAluraApplication.java
package challenge.alura.literAlura;

import challenge.alura.literAlura.service.GutendexService;
import challenge.alura.literAlura.model.GutendexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private GutendexService gutendexService;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		exibirMenu();
	}

	private void exibirMenu() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Menu:");
			System.out.println("1. Inserir dados");
			System.out.println("2. Consultar livros");
			System.out.println("3. Sair");
			System.out.print("Selecione uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine(); // Consumir a nova linha

			switch (opcao) {
				case 1:
					inserirDados(scanner);
					break;
				case 2:
					consultarLivros(scanner);
					break;
				case 3:
					System.out.println("Saindo...");
					scanner.close();
					return;
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	private void inserirDados(Scanner scanner) {
		System.out.println("Inserir dados:");
		// Implementação da lógica para inserir dados (se necessário)
	}

	private void consultarLivros(Scanner scanner) {
		System.out.print("Digite o ano de início do autor: ");
		String authorYearStart = scanner.nextLine();
		System.out.print("Digite os idiomas (separados por vírgulas, ex: en,fr): ");
		String languages = scanner.nextLine();

		Optional<GutendexResponse> books = gutendexService.fetchBooks(authorYearStart, languages);
		if (books.isPresent()) {
			gutendexService.printBooks(books.get());
		} else {
			System.out.println("Erro ao obter livros ou nenhum livro encontrado.");
		}
	}
}
