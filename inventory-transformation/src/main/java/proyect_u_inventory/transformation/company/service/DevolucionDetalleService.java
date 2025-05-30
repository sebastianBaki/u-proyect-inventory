package proyect_u_inventory.transformation.company.service;

import proyect_u_inventory.transformation.company.dto.request.DevolucionDetalleRequest;
import proyect_u_inventory.transformation.company.dto.request.DevolucionesRequest;
import proyect_u_inventory.transformation.company.dto.response.DevolucionesDetalleResponse;
import proyect_u_inventory.transformation.company.dto.response.DevolucionesResponse;

import java.util.List;

public interface DevolucionDetalleService {
    DevolucionesDetalleResponse create(DevolucionDetalleRequest request);
    DevolucionesDetalleResponse getById(Long id);
    List<DevolucionesDetalleResponse> getByAll();
    DevolucionesDetalleResponse updateById(DevolucionDetalleRequest request, Long id);
    void deleteById(Long id);
}
