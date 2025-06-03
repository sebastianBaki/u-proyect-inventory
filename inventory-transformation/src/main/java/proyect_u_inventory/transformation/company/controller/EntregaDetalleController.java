package proyect_u_inventory.transformation.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyect_u_inventory.transformation.company.dto.request.EntregaDetalleRequest;
import proyect_u_inventory.transformation.company.dto.response.EntregaDetalleResponse;
import proyect_u_inventory.transformation.company.exception.BussinesException;
import proyect_u_inventory.transformation.company.service.EntregaDetalleService;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/v1/entrega-detalle")
public class EntregaDetalleController {

    @Autowired
    private EntregaDetalleService service;

    @GetMapping("/{id}")
    private ResponseEntity<?> searchEntregaDetalleById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getEntregaDetalleById(id));
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    private ResponseEntity<List<EntregaDetalleResponse>> searchAllEntregaDetalle() {
        return ResponseEntity.ok(service.getEntregaDetalleAll());
    }

    @PostMapping
    private ResponseEntity<?> createEntregaDetalle(@RequestBody EntregaDetalleRequest entregaDetalleRequest){
        return ResponseEntity.ok(service.creatEntregaDetalle(entregaDetalleRequest));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteEntregaDetalleById(@PathVariable Long id) {
        try {
            service.eliminartEntregaDetalleById(id);
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateEntregaDetalleById(@RequestBody EntregaDetalleRequest entregaRequest, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.updateEntregaById(entregaRequest, id));
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
