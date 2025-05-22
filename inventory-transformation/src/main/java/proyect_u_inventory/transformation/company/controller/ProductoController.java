package proyect_u_inventory.transformation.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyect_u_inventory.transformation.company.dto.request.ProductoRequest;
import proyect_u_inventory.transformation.company.dto.response.ProductoResponse;
import proyect_u_inventory.transformation.company.service.ProductoService;

/**
 * controller de productos
 */
@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    @Autowired
    private ProductoService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> searchProductoById(@PathVariable Long id) {}

    @GetMapping
    public ResponseEntity<?> productosAll() {}

    @PostMapping
    public ResponseEntity<ProductoResponse> createProducto(@RequestBody ProductoRequest request) {}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {}

    @PutMapping("{id}")
    public ResponseEntity<?> updateProducto(@RequestBody ProductoRequest productoRequest, Long id) {}
}
