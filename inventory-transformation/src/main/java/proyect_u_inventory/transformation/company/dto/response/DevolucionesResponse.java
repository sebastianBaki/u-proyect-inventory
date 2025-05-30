package proyect_u_inventory.transformation.company.dto.response;


import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class DevolucionesResponse {
    private Long id;

    private Long empleadoId;

    private LocalDate createDate;

}
