package proyect_u_inventory.transformation.company.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyect_u_inventory.transformation.company.model.entity.Empleados;

/**
 * interface de empleados para repostory
 */
@Repository
public interface EmpleadosRepository extends CrudRepository<Empleados, Long> {
}
