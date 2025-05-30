package proyect_u_inventory.transformation.company.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyect_u_inventory.transformation.company.constants.ErrorMessages;
import proyect_u_inventory.transformation.company.dto.request.DevolucionDetalleRequest;
import proyect_u_inventory.transformation.company.dto.response.DevolucionesDetalleResponse;
import proyect_u_inventory.transformation.company.exception.BussinesException;
import proyect_u_inventory.transformation.company.model.entity.DevolucionDetalle;
import proyect_u_inventory.transformation.company.model.entity.Devoluciones;
import proyect_u_inventory.transformation.company.model.entity.Producto;
import proyect_u_inventory.transformation.company.repository.DevolucionDetalleRepository;
import proyect_u_inventory.transformation.company.repository.DevolucionesRepository;
import proyect_u_inventory.transformation.company.repository.ProductoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DevolucionDetalleServiceImpl implements DevolucionDetalleService {
    @Autowired
    private DevolucionDetalleRepository repository;

    @Autowired
    private DevolucionesRepository devolucionesRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public DevolucionesDetalleResponse create(DevolucionDetalleRequest request) {
        log.info("creando devoluciones_detalles");
        try {
            Optional<Devoluciones> devolucionesDb = devolucionesRepository.findById(request.getDevolucionId());
            Optional<Producto> producto = productoRepository.findById(request.getProductoId());
            if (!devolucionesDb.isPresent()) {
                throw new BussinesException(ErrorMessages.RETURNS_DETAILS_CREATE, "");
            }
            if (!producto.isPresent()) {
                throw new BussinesException(ErrorMessages.RETURNS_DETAILS_CREATE, "");
            }
            DevolucionDetalle devolucion = buildBd(request, producto.get(), devolucionesDb.get());
            repository.save(devolucion);
            return buildResponse(devolucion);

        } catch (BussinesException e ) {
            throw new BussinesException(ErrorMessages.RETURNS_DETAILS_CREATE, "");
        }
    }

    @Override
    public DevolucionesDetalleResponse getById(Long id) {
        log.info("buscando entrega devolucion_detalle por id {}", id);
        Optional<DevolucionDetalle> devoluciones = repository.findById(id);
        if (!devoluciones.isPresent()) {
            throw new BussinesException(ErrorMessages.RETURNS_DETAILS_GET, String.valueOf(id));
        }
        return buildResponse(devoluciones.get());
    }

    @Override
    public List<DevolucionesDetalleResponse> getByAll() {
        log.info("buscando todas las devoluciones_detalle");
        List<DevolucionDetalle> devoluciones = (List<DevolucionDetalle>) repository.findAll();
        return createAll(devoluciones);
    }

    @Override
    public DevolucionesDetalleResponse updateById(DevolucionDetalleRequest request, Long id) {
        log.info("actualizando devolucion_detalle");
        try {
            Optional<Producto> producto = productoRepository.findById(request.getProductoId());
            Optional<Devoluciones> devolucionesBd = devolucionesRepository.findById(request.getDevolucionId());
            Optional<DevolucionDetalle> devolucionDetalle = repository.findById(id);

            if (!producto.isPresent()) {
                throw new BussinesException(ErrorMessages.RETURNS_DETAILS_UPDATE, "");
            }

            if (!devolucionesBd.isPresent()) {
                throw new BussinesException(ErrorMessages.RETURNS_DETAILS_UPDATE, "");
            }

            devolucionDetalle.get().setDevolucion(devolucionesBd.get());
            devolucionDetalle.get().setProducto(producto.get());
            repository.save(devolucionDetalle.get());
            return buildResponse(devolucionDetalle.get());

        } catch (BussinesException e ) {
            throw new BussinesException(ErrorMessages.RETURNS_DETAILS_UPDATE, "");
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("borrando devolucion_detalle {}", id);
        Optional<DevolucionDetalle> devoluciones = repository.findById(id);
        if (!devoluciones.isPresent()) {
            throw new BussinesException(ErrorMessages.DELIVERIES_DETAILS_DELETE, String.valueOf(id));
        }
        repository.deleteById(id);
    }

    private DevolucionDetalle buildBd(DevolucionDetalleRequest devolucionDetalle,
                                      Producto producto,
                                      Devoluciones devoluciones) {
        DevolucionDetalle devolucionDetalleBD = new DevolucionDetalle();

        devolucionDetalleBD.setDevolucion(devoluciones);
        devolucionDetalleBD.setProducto(producto);
        devolucionDetalleBD.setCantidad(devolucionDetalle.getCantidad());
        return devolucionDetalleBD;
    }

    private DevolucionesDetalleResponse buildResponse(DevolucionDetalle devoluciones) {
        return DevolucionesDetalleResponse.builder()
                .id(devoluciones.getId())
                .devolucionId(devoluciones.getDevolucion().getId())
                .productoId(devoluciones.getProducto().getId())
                .createDate(devoluciones.getCreateDate())
                .build();
    }

    private List<DevolucionesDetalleResponse> createAll(List<DevolucionDetalle> devolucionesList) {
        List<DevolucionesDetalleResponse> responses = new ArrayList<>();
        for (DevolucionDetalle devoluciones: devolucionesList) {
            responses.add(buildResponse(devoluciones));
        }
        return responses;
    }
}
