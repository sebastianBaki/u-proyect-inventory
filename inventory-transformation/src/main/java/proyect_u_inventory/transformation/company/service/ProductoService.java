package proyect_u_inventory.transformation.company.service;

import proyect_u_inventory.transformation.company.dto.request.ProductoRequest;
import proyect_u_inventory.transformation.company.dto.response.ProductoResponse;

import java.util.List;

/**
 * interfa de producto service
 */
public interface ProductoService {
    ProductoResponse createProduct(ProductoRequest producto);
    List<ProductoResponse> getAllProducts();

    ProductoResponse getProductoById(Long id);

    void deleteProductById(Long id);
    ProductoResponse updateProductById(ProductoRequest request, Long id);
}
