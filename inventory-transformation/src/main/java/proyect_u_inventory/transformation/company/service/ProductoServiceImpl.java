package proyect_u_inventory.transformation.company.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyect_u_inventory.transformation.company.constants.ErrorMessages;
import proyect_u_inventory.transformation.company.dto.request.ProductoRequest;
import proyect_u_inventory.transformation.company.dto.response.ProductoResponse;
import proyect_u_inventory.transformation.company.exception.BussinesException;
import proyect_u_inventory.transformation.company.model.entity.Producto;
import proyect_u_inventory.transformation.company.repository.ProductoRepository;
import proyect_u_inventory.transformation.company.utils.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * servicio de producto
 */
@Slf4j
@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Override
    @Transactional
    public ProductoResponse createProduct(ProductoRequest producto) {
        log.info("creando producto {}", producto.getName());
        try {
            Producto productoBuild = buildProducto(producto);
            productoRepository.save(productoBuild);
            return createProductoResponse(productoBuild);
        } catch (BussinesException e) {
            throw new BussinesException(ErrorMessages.EMPLOYEE_CREATE, producto.getName());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponse> getAllProducts() {
        log.info("Buscando todos los empleados");
        List<Producto> productosDB = (List<Producto>) productoRepository.findAll();
        return buildProductoAll(productosDB);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponse getProductoById(Long id) {
        log.info("buscando producto con id {}", id);
        Optional<Producto> producto = productoRepository.findById(id);
        if (!producto.isPresent()) {
            throw new BussinesException(ErrorMessages.PRODUCT_GET, String.valueOf(id));
        }
        return createProductoResponse(producto.get());
    }

    @Override
    public void deleteProductById(Long id) {
        log.info("borrando producto {}", id);
        Optional<Producto> productoBd = productoRepository.findById(id);
        if (!productoBd.isPresent()) {
            throw new BussinesException(ErrorMessages.PRODUCT_DELETE, String.valueOf(id));
        }
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoResponse updateProductById(ProductoRequest request, Long id) {
        log.info("Actualizando producto {}", id);
        Optional<Producto> productoBd = productoRepository.findById(id);
        if (!productoBd.isPresent()) {
            throw new BussinesException(ErrorMessages.PRODUCT_UPDATE, String.valueOf(id));
        }

        productoBd.get().setName(request.getName());
        productoBd.get().setDesciption(request.getDescription());
        productoBd.get().setStatus(request.getStatus());
        productoBd.get().setStock(request.getStock());

        if (request.getPicture().length > 0) {
            productoBd.get().setPicture(request.getPicture());
        }

        productoRepository.save(productoBd.get());
        return createProductoResponse(productoBd.get());
    }

    private Producto buildProducto(ProductoRequest request){
        Producto producto = new Producto();
        producto.setName(request.getName());
        producto.setDesciption(request.getDescription());
        producto.setStatus(request.getStatus());
        producto.setStock(request.getStock());
        producto.setPicture(request.getPicture());
        return producto;
    }

    private ProductoResponse createProductoResponse(Producto producto) {
        byte[] imageDescompressd = Util.decompressZLib(producto.getPicture());
        return ProductoResponse.builder()
                .id(producto.getId())
                .name(producto.getName())
                .description(producto.getDesciption())
                .status(producto.getStatus())
                .stock(producto.getStock())
                .picture(imageDescompressd)
                .build();
    }

    private List<ProductoResponse> buildProductoAll(List<Producto> productos) {
        List<ProductoResponse> productoResponses = new ArrayList<>();
        for (Producto producto: productos) {
            productoResponses.add(createProductoResponse(producto));
        }
        return productoResponses;
    }
}

