package challenge.alura.literAlura.repository;

import challenge.alura.literAlura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b")
    List<Book> findAllBooks();

    @Query("select b from Book b where b.languages like %?1%")
    List<Book> findBooksByLanguage(String language);
}
