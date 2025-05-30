package proyect_u_inventory.transformation.company.dto.request;

import lombok.Data;

@Data
public class DevolucionDetalleRequest {

    private Long devolucionId;

    private Long productoId;

    private Integer cantidad;
}
