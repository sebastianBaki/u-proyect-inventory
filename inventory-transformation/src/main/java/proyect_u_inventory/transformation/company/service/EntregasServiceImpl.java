package proyect_u_inventory.transformation.company.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyect_u_inventory.transformation.company.constants.ErrorMessages;
import proyect_u_inventory.transformation.company.dto.request.EntregaRequest;
import proyect_u_inventory.transformation.company.dto.response.EntregaResponse;
import proyect_u_inventory.transformation.company.exception.BussinesException;
import proyect_u_inventory.transformation.company.model.entity.Empleados;
import proyect_u_inventory.transformation.company.model.entity.Entrega;
import proyect_u_inventory.transformation.company.repository.EmpleadosRepository;
import proyect_u_inventory.transformation.company.repository.EntregaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * entrega service
 */
@Service
@Slf4j
public class EntregasServiceImpl implements EntregaService {

    @Autowired
    private EntregaRepository repository;
    @Autowired
    private EmpleadosRepository empleadoRepository;
    @Override
    @Transactional
    public EntregaResponse crearteEntrega(EntregaRequest entrega) {
        log.info("creando entrega");
        try {
            Optional<Empleados> empleados = empleadoRepository.findById(Long.valueOf(entrega.getEmpleadoId()));
            if (!empleados.isPresent()) {
                throw new BussinesException(ErrorMessages.DELIVERIES_CREATE, "");
            }
            Entrega entregaBuild = builEntrega(entrega, empleados.get());
            repository.save(entregaBuild);
            return createResponseEntrega(entregaBuild);
        } catch (BussinesException e) {
            throw new BussinesException(ErrorMessages.DELIVERIES_CREATE, "");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public EntregaResponse getEntregaById(Long id) {
        log.info("buscando entrega por id {}", id);
        Optional<Entrega> entrega = repository.findById(id);
        if (!entrega.isPresent()) {
            throw new BussinesException(ErrorMessages.DELIVERIES_GET, String.valueOf(id));
        }
        return createResponseEntrega(entrega.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntregaResponse> getAllEntregas() {
        log.info("buscando todas las entregas");
        List<Entrega> entregasBd = (List<Entrega>) repository.findAll();
        return crearAllResponseEntregas(entregasBd);
    }

    @Override
    public EntregaResponse updateEntregaById(EntregaRequest entregaRequest, Long id) {
        log.info("actualizando entrega {} ", id);
        Optional<Empleados> empleados = empleadoRepository.findById(entregaRequest.getEmpleadoId());
        Optional<Entrega> entrega = repository.findById(id);
        if (!empleados.isPresent()) {
            throw new BussinesException(ErrorMessages.EMPLOYEE_GET, String.valueOf(entregaRequest.getEmpleadoId()));
        }
        if (!entrega.isPresent()) {
            throw new BussinesException(ErrorMessages.DELIVERIES_UPDATE, String.valueOf(id));
        }
        entrega.get().setEmpleado(empleados.get());
        entrega.get().setFechaEntrega(entregaRequest.getFechaEntrega());
        repository.save(entrega.get());
        return createResponseEntrega(entrega.get());
    }

    @Override
    public void deleteEntregaById(Long id) {
        log.info("borrando entrega {}", id);
        Optional<Entrega> entregaBd = repository.findById(id);
        if (!entregaBd.isPresent()) {
            throw new BussinesException(ErrorMessages.DELIVERIES_DELETE, String.valueOf(id));
        }
        repository.deleteById(id);
    }

    private Entrega builEntrega(EntregaRequest entregaIn, Empleados empleado) {
        Entrega entregaBd = new Entrega();
        entregaBd.setEmpleado(empleado);
        entregaBd.setFechaEntrega(entregaIn.getFechaEntrega());
        return entregaBd;
    }

    private EntregaResponse createResponseEntrega(Entrega entrega) {
        return EntregaResponse.builder()
                .id(entrega.getId().toString())
                .empleadoId(entrega.getEmpleado().getId())
                .fechaEntrega(entrega.getFechaEntrega())
                .fechaCreacion(entrega.getCreationDate())
                .build();
    }

    private List<EntregaResponse> crearAllResponseEntregas(List<Entrega> entregaList) {
        List<EntregaResponse> responses = new ArrayList<>();
        for (Entrega entrega : entregaList) {
            responses.add(createResponseEntrega(entrega));
        }
        return responses;
    }
}
