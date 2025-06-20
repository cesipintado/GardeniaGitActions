package GardeniaProyect.Gardenia.app.service;

import GardeniaProyect.Gardenia.app.repository.IDetalleOrdenRepository;
import GardeniaProyect.Gardenia.infrastructure.entity.DetalleOrden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService{
	
	@Autowired
	private IDetalleOrdenRepository detalleOrdenRepository;

	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenRepository.save(detalleOrden);
	}

}
