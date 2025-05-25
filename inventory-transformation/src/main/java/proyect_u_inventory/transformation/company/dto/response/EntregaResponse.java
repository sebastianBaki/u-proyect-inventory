package proyect_u_inventory.transformation.company.dto.response;

import lombok.Builder;
import lombok.Data;
import proyect_u_inventory.transformation.company.model.entity.Empleados;

import java.time.LocalDate;

/**
 * entrega response
 */
@Data
@Builder
public class EntregaResponse {
    private String id;
    private Long empleadoId;
    private LocalDate fechaEntrega;
    private LocalDate fechaCreacion;
}
