package proyect_u_inventory.transformation.company.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * producto respuesta
 */
@Data
@Builder
public class ProductoResponse {
    private Long id;
    private String name;
    private String description;
    protected String status;
    private int stock;
    private byte[] picture;
}
