package proyect_u_inventory.transformation.company.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class EntregaDetalleResponse {
    private String id;
    private Long entregaId;
    private Long productoId;
    private int amount;
    private LocalDate createDate;
}
