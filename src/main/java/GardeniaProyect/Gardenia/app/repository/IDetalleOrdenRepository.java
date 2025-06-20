package GardeniaProyect.Gardenia.app.repository;

import GardeniaProyect.Gardenia.infrastructure.entity.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {

}
