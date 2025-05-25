package proyect_u_inventory.transformation.company.service;

import proyect_u_inventory.transformation.company.dto.request.EntregaRequest;
import proyect_u_inventory.transformation.company.dto.response.EntregaResponse;

import java.util.List;

/**
 *
 */
public interface EntregaService {
    EntregaResponse crearteEntrega(EntregaRequest entrega);
    EntregaResponse getEntregaById(Long id);
    List<EntregaResponse> getAllEntregas();
    EntregaResponse updateEntregaById(EntregaRequest entregaRequest, Long id);
    void deleteEntregaById(Long id);
}
