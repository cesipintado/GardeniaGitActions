package GardeniaProyect.Gardenia.app.repository;

import GardeniaProyect.Gardenia.infrastructure.entity.Orden;
import GardeniaProyect.Gardenia.infrastructure.entity.Usuario;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer> {

    List<Orden> findByUsuario(Usuario usuario);
}
