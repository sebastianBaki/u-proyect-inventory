package proyect_u_inventory.transformation.company.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DevolucionesDetalleResponse {
    private Long id;
    private Long devolucionId;
    private Long productoId;
    private Integer cantidad;
    private LocalDate createDate;
}
