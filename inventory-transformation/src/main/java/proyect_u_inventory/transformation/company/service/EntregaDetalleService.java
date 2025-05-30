package proyect_u_inventory.transformation.company.service;

import proyect_u_inventory.transformation.company.dto.request.EntregaDetalleRequest;
import proyect_u_inventory.transformation.company.dto.response.EntregaDetalleResponse;

import java.util.List;

public interface EntregaDetalleService {
    EntregaDetalleResponse creatEntregaDetalle(EntregaDetalleRequest request);
    EntregaDetalleResponse getEntregaDetalleById(Long id);
    List<EntregaDetalleResponse> getEntregaDetalleAll();

    EntregaDetalleResponse updateEntregaById(EntregaDetalleRequest request, Long id);

    void eliminartEntregaDetalleById(Long id);

}
