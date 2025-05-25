package proyect_u_inventory.transformation.company.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * entrega request
 */
@Data
public class EntregaRequest {
    @JsonProperty("empleado_id")
    private Long empleadoId;
    @JsonProperty("fecha_entrega")
    private LocalDate fechaEntrega;
}
