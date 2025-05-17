package proyect_u_inventory.transformation.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyect_u_inventory.transformation.company.dto.request.EmpleadoRequest;
import proyect_u_inventory.transformation.company.dto.response.EmpleadoResponse;
import proyect_u_inventory.transformation.company.service.EmpleadoService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * controller de empleados
 */
@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadosController {
    @Autowired
    private EmpleadoService service;

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> searchEmpleadoById(@PathVariable Long id) {
        EmpleadoResponse response = service.getById(id);
        if(response == null ) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoResponse>> searchAllEmpleados() {
        List<EmpleadoResponse> responses = service.getAllEmpleados();
            responses.sort(Comparator.comparing(EmpleadoResponse::getId));
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponse> crearteEmpleado(@RequestBody EmpleadoRequest request) {
        return ResponseEntity.ok(service.createEmpleado(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpleadoById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> updateEmpleadoById(@RequestBody EmpleadoRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(service.updateEmpleadoById(request, id));
    }


}
