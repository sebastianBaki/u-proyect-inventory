package proyect_u_inventory.transformation.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import proyect_u_inventory.transformation.company.dto.request.ProductoRequest;
import proyect_u_inventory.transformation.company.dto.response.ProductoResponse;
import proyect_u_inventory.transformation.company.exception.BussinesException;
import proyect_u_inventory.transformation.company.service.ProductoService;
import proyect_u_inventory.transformation.company.utils.Util;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * controller de productos
 */
@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    @Autowired
    private ProductoService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> searchProductoById(@PathVariable Long id) {
        try {
            ProductoResponse response = service.getProductoById(id);
            return ResponseEntity.ok(response);
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> productosAll() {
        List<ProductoResponse> responses = service.getAllProducts();
        responses.sort(Comparator.comparing(ProductoResponse::getId));
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> createProducto(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("status") String status,
            @RequestParam("stock") int stock,
            @RequestParam("picture")MultipartFile picture) throws IOException {
        ProductoRequest request = new ProductoRequest();
        request.setName(name);
        request.setDescription(description);
        request.setStatus(status);
        request.setStock(stock);
        request.setPicture(Util.compressZlib(picture.getBytes()));
        return ResponseEntity.ok(service.createProduct(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        try {
            service.deleteProductById(id);
            return ResponseEntity.noContent().build();
        } catch (BussinesException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProducto(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("status") String status,
            @RequestParam("stock") int stock,
            @RequestParam("picture")MultipartFile picture,
            @PathVariable Long id) throws IOException{
        ProductoRequest productoRequest = new ProductoRequest();
        productoRequest.setName(name);
        productoRequest.setDescription(description);
        productoRequest.setStatus(status);
        productoRequest.setStock(stock);
        productoRequest.setPicture(Util.compressZlib(picture.getBytes()));
        try {
            return ResponseEntity.ok(service.updateProductById(productoRequest, id));
        } catch (BussinesException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
