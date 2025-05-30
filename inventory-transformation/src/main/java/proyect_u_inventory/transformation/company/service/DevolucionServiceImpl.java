package proyect_u_inventory.transformation.company.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyect_u_inventory.transformation.company.constants.ErrorMessages;
import proyect_u_inventory.transformation.company.dto.request.DevolucionesRequest;
import proyect_u_inventory.transformation.company.dto.response.DevolucionesResponse;
import proyect_u_inventory.transformation.company.exception.BussinesException;
import proyect_u_inventory.transformation.company.model.entity.*;
import proyect_u_inventory.transformation.company.repository.DevolucionesRepository;
import proyect_u_inventory.transformation.company.repository.EmpleadosRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class DevolucionServiceImpl implements DevolucionService {

    @Autowired
    private DevolucionesRepository repository;

    @Autowired
    private EmpleadosRepository empleadosRepository;


    @Override
    public DevolucionesResponse createDevoluciones(DevolucionesRequest request) {
        log.info("creando devoluciones");
        try {
            Optional<Empleados> empleadosBd = empleadosRepository.findById(request.getEmpleadoId());
            if (!empleadosBd.isPresent()) {
                throw new BussinesException(ErrorMessages.RETURNS_CREATE, "");
            }

            Devoluciones devoluciones = buildDevoluciones(empleadosBd.get());
            repository.save(devoluciones);
            return buildResponse(devoluciones);

        } catch (BussinesException e ) {
            throw new BussinesException(ErrorMessages.RETURNS_CREATE, "");
        }
    }

    @Override
    public DevolucionesResponse getById(Long id) {
        log.info("buscando entrega devolucion por id {}", id);
        Optional<Devoluciones> devoluciones = repository.findById(id);
        if (!devoluciones.isPresent()) {
            throw new BussinesException(ErrorMessages.RETURNS_GET, String.valueOf(id));
        }
        return buildResponse(devoluciones.get());
    }

    @Override
    public List<DevolucionesResponse> getByAll() {
        log.info("buscando todas las devoluciones");
        List<Devoluciones> devoluciones = (List<Devoluciones>) repository.findAll();
        return createAll(devoluciones);
    }

    @Override
    public DevolucionesResponse updateById(DevolucionesRequest request, Long id) {
        log.info("actualizando devolucion");
        try {
            Optional<Empleados> empleados = empleadosRepository.findById(request.getEmpleadoId());
            Optional<Devoluciones> devolucionesBd = repository.findById(id);

            if (!empleados.isPresent()) {
                throw new BussinesException(ErrorMessages.RETURNS_UPDATE, "");
            }

            if (!devolucionesBd.isPresent()) {
                throw new BussinesException(ErrorMessages.RETURNS_UPDATE, "");
            }

            devolucionesBd.get().setEmpleados(empleados.get());
            repository.save(devolucionesBd.get());
            return buildResponse(devolucionesBd.get());

        } catch (BussinesException e ) {
            throw new BussinesException(ErrorMessages.DELIVERIES_DETAILS_UPDATE, "");
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("borrando devoluciones {}", id);
        Optional<Devoluciones> devoluciones = repository.findById(id);
        if (!devoluciones.isPresent()) {
            throw new BussinesException(ErrorMessages.DELIVERIES_DETAILS_DELETE, String.valueOf(id));
        }
        repository.deleteById(id);
    }

    private Devoluciones buildDevoluciones(Empleados empleado) {
        Devoluciones devoluciones = new Devoluciones();
        devoluciones.setEmpleados(empleado);
        return devoluciones;
    }

    private DevolucionesResponse buildResponse(Devoluciones devoluciones) {
        return DevolucionesResponse.builder()
                .empleadoId(devoluciones.getEmpleados().getId())
                .createDate(devoluciones.getCreateDate())
                .build();
    }

    private List<DevolucionesResponse> createAll(List<Devoluciones> devolucionesList) {
        List<DevolucionesResponse> responses = new ArrayList<>();
        for (Devoluciones devoluciones: devolucionesList) {
            responses.add(buildResponse(devoluciones));
        }
        return responses;
    }


}
