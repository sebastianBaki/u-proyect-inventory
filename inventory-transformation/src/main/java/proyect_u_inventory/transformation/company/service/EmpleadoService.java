package proyect_u_inventory.transformation.company.service;


import proyect_u_inventory.transformation.company.dto.request.EmpleadoRequest;
import proyect_u_inventory.transformation.company.dto.response.EmpleadoResponse;
import proyect_u_inventory.transformation.company.model.entity.Empleados;

import java.util.List;

/**
 * service de empleados
 */
public interface EmpleadoService {

    EmpleadoResponse createEmpleado();
    List<Empleados> getAll();
    Empleados getById(Long id);
    void deleteById(Long id);
    EmpleadoResponse updateEmpleadoById(EmpleadoRequest request, Long id);
}
