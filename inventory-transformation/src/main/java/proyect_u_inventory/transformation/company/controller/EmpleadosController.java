package proyect_u_inventory.transformation.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyect_u_inventory.transformation.company.dto.request.EmpleadoRequest;
import proyect_u_inventory.transformation.company.dto.response.EmpleadoResponse;
import proyect_u_inventory.transformation.company.exception.BussinesException;
import proyect_u_inventory.transformation.company.service.EmpleadoService;

import java.util.Comparator;
import java.util.List;

/**
 * controller de empleados
 */
@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadosController {
    @Autowired
    private EmpleadoService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> searchEmpleadoById(@PathVariable Long id) {
        try {
            EmpleadoResponse response = service.getById(id);
            return ResponseEntity.ok(response);
        } catch (BussinesException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> searchAllEmpleados() {
        try {
            List<EmpleadoResponse> responses = service.getAllEmpleados();
            responses.sort(Comparator.comparing(EmpleadoResponse::getId));
            return ResponseEntity.ok(responses);
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponse> crearteEmpleado(@RequestBody EmpleadoRequest request) {
        return ResponseEntity.ok(service.createEmpleado(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpleadoById(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmpleadoById(@RequestBody EmpleadoRequest request, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.updateEmpleadoById(request, id));
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
