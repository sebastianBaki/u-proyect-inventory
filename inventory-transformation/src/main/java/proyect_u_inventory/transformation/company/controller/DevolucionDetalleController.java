package proyect_u_inventory.transformation.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyect_u_inventory.transformation.company.dto.request.DevolucionDetalleRequest;
import proyect_u_inventory.transformation.company.dto.request.DevolucionesRequest;
import proyect_u_inventory.transformation.company.dto.response.DevolucionesDetalleResponse;
import proyect_u_inventory.transformation.company.exception.BussinesException;
import proyect_u_inventory.transformation.company.service.DevolucionDetalleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devolucion-detalle")
public class DevolucionDetalleController {
    @Autowired
    private DevolucionDetalleService service;

    @GetMapping("/{id}")
    private ResponseEntity<?> searchById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    private ResponseEntity<List<DevolucionesDetalleResponse>> searchAll() {
        return ResponseEntity.ok(service.getByAll());
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody DevolucionDetalleRequest request){
        return ResponseEntity.ok(service.create(request));
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
    private ResponseEntity<?> updateById(@RequestBody DevolucionDetalleRequest request, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.updateById(request, id));
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
