package proyect_u_inventory.transformation.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyect_u_inventory.transformation.company.dto.request.DevolucionesRequest;
import proyect_u_inventory.transformation.company.dto.request.EntregaDetalleRequest;
import proyect_u_inventory.transformation.company.dto.response.DevolucionesResponse;
import proyect_u_inventory.transformation.company.dto.response.EntregaDetalleResponse;
import proyect_u_inventory.transformation.company.exception.BussinesException;
import proyect_u_inventory.transformation.company.service.DevolucionService;
import proyect_u_inventory.transformation.company.service.EntregaDetalleService;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/v1/devolucion")
public class DevolucionController {
    @Autowired
    private DevolucionService service;

    @GetMapping("/{id}")
    private ResponseEntity<?> searchById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    private ResponseEntity<List<DevolucionesResponse>> searchAll() {
        return ResponseEntity.ok(service.getByAll());
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody DevolucionesRequest request){
        return ResponseEntity.ok(service.createDevoluciones(request));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateById(@RequestBody DevolucionesRequest request, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.updateById(request, id));
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
