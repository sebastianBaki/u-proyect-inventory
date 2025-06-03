package proyect_u_inventory.transformation.company.service;


import proyect_u_inventory.transformation.company.dto.request.EmpleadoRequest;
import proyect_u_inventory.transformation.company.dto.response.ContadorItemsResponse;
import proyect_u_inventory.transformation.company.dto.response.EmpleadoResponse;

import java.util.List;

/**
 * service de empleados
 */
public interface EmpleadoService {

    EmpleadoResponse createEmpleado(EmpleadoRequest empleado);
    List<EmpleadoResponse> getAllEmpleados();
    EmpleadoResponse getById(Long id);
    void deleteById(Long id);
    EmpleadoResponse updateEmpleadoById(EmpleadoRequest request, Long id);

    ContadorItemsResponse contarItems();
}
