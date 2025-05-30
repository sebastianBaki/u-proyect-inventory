package proyect_u_inventory.transformation.company.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyect_u_inventory.transformation.company.model.entity.DevolucionDetalle;
@Repository
public interface DevolucionDetalleRepository extends CrudRepository<DevolucionDetalle, Long> {
}
