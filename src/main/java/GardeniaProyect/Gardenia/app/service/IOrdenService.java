package GardeniaProyect.Gardenia.app.service;

import GardeniaProyect.Gardenia.infrastructure.entity.Orden;
import GardeniaProyect.Gardenia.infrastructure.entity.Usuario;
import java.util.List;
import java.util.Optional;



public interface IOrdenService {

    List<Orden> findAll();

    Optional<Orden> findById(Integer id);

    Orden save(Orden orden);

    String generarNumeroOrden();

    List<Orden> findByUsuario(Usuario usuario);
}
