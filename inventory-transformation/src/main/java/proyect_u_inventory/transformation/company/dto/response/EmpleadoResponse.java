package proyect_u_inventory.transformation.company.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * objeto de salida para empleados
 */
@Data
@Builder
public class EmpleadoResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private LocalDate creationDate;
    private boolean isActive;
}
