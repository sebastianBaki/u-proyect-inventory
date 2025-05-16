package proyect_u_inventory.transformation.company.dto.request;

import lombok.Data;

/**
 * objeto de entrada desde la api para empleado
 */
@Data
public class EmpleadoRequest {

    private String idenficationNumber;
    private String name;
    private String address;
    private String phone;
    private String email;
    private boolean isActive;
}
