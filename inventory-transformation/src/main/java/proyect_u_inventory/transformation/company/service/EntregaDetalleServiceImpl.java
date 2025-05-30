package proyect_u_inventory.transformation.company.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyect_u_inventory.transformation.company.constants.ErrorMessages;
import proyect_u_inventory.transformation.company.dto.request.EntregaDetalleRequest;
import proyect_u_inventory.transformation.company.dto.response.EntregaDetalleResponse;
import proyect_u_inventory.transformation.company.exception.BussinesException;
import proyect_u_inventory.transformation.company.model.entity.Entrega;
import proyect_u_inventory.transformation.company.model.entity.EntregaDetalle;
import proyect_u_inventory.transformation.company.model.entity.Producto;
import proyect_u_inventory.transformation.company.repository.EntregaDetalleRepository;
import proyect_u_inventory.transformation.company.repository.EntregaRepository;
import proyect_u_inventory.transformation.company.repository.ProductoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EntregaDetalleServiceImpl implements EntregaDetalleService {

    @Autowired
    private EntregaDetalleRepository repository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private EntregaRepository entregaRepository;


    @Override
    public EntregaDetalleResponse creatEntregaDetalle(EntregaDetalleRequest request) {
        log.info("creando entrega_detalle");
        try {
            Optional<Entrega> entregaBd = entregaRepository.findById(request.getEntregaId());
            Optional<Producto> productoBd = productoRepository.findById(request.getProductoId());

            if (!entregaBd.isPresent()) {
                throw new BussinesException(ErrorMessages.DELIVERIES_DETAILS_CREATE, "");
            }

            if (!productoBd.isPresent()) {
                throw new BussinesException(ErrorMessages.DELIVERIES_DETAILS_CREATE, "");
            }
            EntregaDetalle entregaDetalle = builEntregaDetalle(request, entregaBd.get(), productoBd.get());
            repository.save(entregaDetalle);
            return creatEntregaDetalleResponse(entregaDetalle);

        } catch (BussinesException e ) {
            throw new BussinesException(ErrorMessages.DELIVERIES_DETAILS_CREATE, "");
        }
    }

    @Override
    public EntregaDetalleResponse getEntregaDetalleById(Long id) {
        log.info("buscando entrega detalle por id {}", id);
        Optional<EntregaDetalle> entregaDetalle = repository.findById(id);
        if(!entregaDetalle.isPresent()) {
            throw new BussinesException(ErrorMessages.DELIVERIES_DELETE, String.valueOf(id));
        }
        return creatEntregaDetalleResponse(entregaDetalle.get());
    }

    @Override
    public List<EntregaDetalleResponse> getEntregaDetalleAll() {
        log.info("buscando todas las entregas detalle");
        List<EntregaDetalle> entregasBd = (List<EntregaDetalle>) repository.findAll();
        return crearAll(entregasBd);
    }

    @Override
    public EntregaDetalleResponse updateEntregaById(EntregaDetalleRequest request, Long id) {
        log.info("actualizando entrega_detalle");
        try {
            Optional<Entrega> entregaBd = entregaRepository.findById(request.getEntregaId());
            Optional<Producto> productoBd = productoRepository.findById(request.getProductoId());
            Optional<EntregaDetalle> entregaDetalleBd = repository.findById(id);

            if (!entregaDetalleBd.isPresent()) {
                throw new BussinesException(ErrorMessages.DELIVERIES_DETAILS_UPDATE, "");
            }

            if (!entregaBd.isPresent()) {
                throw new BussinesException(ErrorMessages.DELIVERIES_DETAILS_UPDATE, "");
            }

            if (!productoBd.isPresent()) {
                throw new BussinesException(ErrorMessages.DELIVERIES_DETAILS_UPDATE, "");
            }
            entregaDetalleBd.get().setEntrega(entregaBd.get());
            entregaDetalleBd.get().setProducto(productoBd.get());
            entregaDetalleBd.get().setAmount(request.getAmount());
            repository.save(entregaDetalleBd.get());
            return creatEntregaDetalleResponse(entregaDetalleBd.get());

        } catch (BussinesException e ) {
            throw new BussinesException(ErrorMessages.DELIVERIES_DETAILS_UPDATE, "");
        }
    }

    @Override
    public void eliminartEntregaDetalleById(Long id) {
        log.info("borrando entrega_detalles {}", id);
        Optional<EntregaDetalle> entregaDetalle = repository.findById(id);
        if (!entregaDetalle.isPresent()) {
            throw new BussinesException(ErrorMessages.DELIVERIES_DETAILS_DELETE, String.valueOf(id));
        }
        repository.deleteById(id);
    }

    private EntregaDetalle builEntregaDetalle (EntregaDetalleRequest entregaDetalleRequest,
                                               Entrega entrega,
                                               Producto producto) {
        EntregaDetalle entregaDetalle = new EntregaDetalle();
        entregaDetalle.setEntrega(entrega);
        entregaDetalle.setProducto(producto);
        entregaDetalle.setAmount(entregaDetalleRequest.getAmount());
        return entregaDetalle;
    }

    private EntregaDetalleResponse creatEntregaDetalleResponse (EntregaDetalle bd) {
        return EntregaDetalleResponse.builder()
                .entregaId(bd.getEntrega().getId())
                .productoId(bd.getProducto().getId())
                .amount(bd.getAmount())
                .build();
    }

    private List<EntregaDetalleResponse> crearAll(List<EntregaDetalle> bdList) {
        List<EntregaDetalleResponse> responses = new ArrayList<>();
        for (EntregaDetalle bd: bdList) {
            responses.add(creatEntregaDetalleResponse(bd));
        }
        return responses;
    }
}
