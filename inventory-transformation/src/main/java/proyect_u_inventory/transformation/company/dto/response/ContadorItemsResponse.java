package proyect_u_inventory.transformation.company.dto.response;

import lombok.Data;

@Data
public class ContadorItemsResponse {
    private int empleados;
    private int productos;
    private int entregas;
    private int devoluciones;
}
