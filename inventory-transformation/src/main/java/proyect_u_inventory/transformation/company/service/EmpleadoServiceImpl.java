package proyect_u_inventory.transformation.company.service;

import proyect_u_inventory.transformation.company.dto.request.EmpleadoRequest;
import proyect_u_inventory.transformation.company.dto.response.EmpleadoResponse;
import proyect_u_inventory.transformation.company.model.entity.Empleados;

import java.util.List;

/**
 * implementacion de empleados
 */
public class EmpleadoServiceImpl implements EmpleadoService{
    @Override
    public EmpleadoResponse createEmpleado() {
        return null;
    }

    @Override
    public List<Empleados> getAll() {
        return null;
    }

    @Override
    public Empleados getById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public EmpleadoResponse updateEmpleadoById(EmpleadoRequest request, Long id) {
        return null;
    }
}
