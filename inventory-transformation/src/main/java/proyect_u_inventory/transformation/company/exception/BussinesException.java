package proyect_u_inventory.transformation.company.exception;

import lombok.extern.java.Log;

public class BussinesException extends RuntimeException{
    public BussinesException (String message, String id) {
        super("Se presenta error en " + message + " para el id: " + id);
    }
}
