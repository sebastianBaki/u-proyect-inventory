package proyect_u_inventory.transformation.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyect_u_inventory.transformation.company.dto.request.EntregaRequest;
import proyect_u_inventory.transformation.company.dto.response.EntregaResponse;
import proyect_u_inventory.transformation.company.exception.BussinesException;
import proyect_u_inventory.transformation.company.service.EntregaService;

import java.util.List;

/**
 * controller de entrega
 */
@RestController
@RequestMapping("/api/v1/entrega")
public class EntregaController {
    @Autowired
    private EntregaService service;
    @GetMapping("/{id}")
    private ResponseEntity<?> searchEntregaById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getEntregaById(id));
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    private ResponseEntity<List<EntregaResponse>> searchAllEntregas() {
        return ResponseEntity.ok(service.getAllEntregas());
    }

    @PostMapping
    private ResponseEntity<?> createEntrega(@RequestBody EntregaRequest entrega){
        return ResponseEntity.ok(service.crearteEntrega(entrega));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteEntregaById(@PathVariable Long id) {
        try {
            service.deleteEntregaById(id);
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateEntregaById(@RequestBody EntregaRequest entregaRequest,@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.updateEntregaById(entregaRequest, id));
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
