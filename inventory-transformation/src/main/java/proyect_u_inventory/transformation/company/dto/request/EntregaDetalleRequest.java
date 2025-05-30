package proyect_u_inventory.transformation.company.dto.request;

import lombok.Data;

@Data
public class EntregaDetalleRequest {

    private Long entregaId;

    private Long productoId;

    private int amount;

}
