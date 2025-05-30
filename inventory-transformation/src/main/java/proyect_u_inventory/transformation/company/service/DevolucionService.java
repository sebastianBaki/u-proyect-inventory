package proyect_u_inventory.transformation.company.service;

import proyect_u_inventory.transformation.company.dto.request.DevolucionesRequest;
import proyect_u_inventory.transformation.company.dto.response.DevolucionesResponse;

import java.util.List;

public interface DevolucionService {
    DevolucionesResponse createDevoluciones(DevolucionesRequest request);
    DevolucionesResponse getById(Long id);
    List<DevolucionesResponse> getByAll();
    DevolucionesResponse updateById(DevolucionesRequest request, Long id);
    void deleteById(Long id);
}
