package GardeniaProyect.Gardenia.app.repository;

import GardeniaProyect.Gardenia.infrastructure.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

}
