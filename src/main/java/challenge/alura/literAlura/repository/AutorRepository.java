package challenge.alura.literAlura.repository;

import challenge.alura.literAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("select a from Autor a")
    List<Autor> findAllAuthors();

    @Query("select a from Autor a where a.deathYear is null or a.deathYear > :year")
    List<Autor> findAuthorsAliveInYear(int year);
}
