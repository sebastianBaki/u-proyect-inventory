package proyect_u_inventory.transformation.company.dto.request;

import lombok.Data;

/**
 * producto peticion entrante
 */
@Data
public class ProductoRequest {
    private String name;
    private String description;
    private String status;
    private int stock;
    private byte[] picture;
}
