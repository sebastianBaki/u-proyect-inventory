package proyect_u_inventory.transformation.company.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyect_u_inventory.transformation.company.model.entity.Devoluciones;
@Repository
public interface DevolucionesRepository extends CrudRepository<Devoluciones, Long> {
}
