package proyect_u_inventory.transformation.company.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyect_u_inventory.transformation.company.model.entity.EntregaDetalle;

@Repository
public interface EntregaDetalleRepository extends CrudRepository<EntregaDetalle, Long> {
}
