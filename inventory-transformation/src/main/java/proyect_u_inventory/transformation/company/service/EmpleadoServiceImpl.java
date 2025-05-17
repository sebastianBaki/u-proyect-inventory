package proyect_u_inventory.transformation.company.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyect_u_inventory.transformation.company.dto.request.EmpleadoRequest;
import proyect_u_inventory.transformation.company.dto.response.EmpleadoResponse;
import proyect_u_inventory.transformation.company.model.entity.Empleados;
import proyect_u_inventory.transformation.company.repository.EmpleadosRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * implementacion de empleados
 */
@Slf4j
@Service
public class EmpleadoServiceImpl implements EmpleadoService  {

    @Autowired
    private EmpleadosRepository repository;
    @Override
    @Transactional
    public EmpleadoResponse createEmpleado(EmpleadoRequest empleadoRequest) {
        log.info("creando empleado : {} ", empleadoRequest.getIdenficationNumber());
        Empleados empleados = buildEmpleadosForSave(empleadoRequest);
        repository.save(empleados);
        return createEmpleadoResponse(empleados);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoResponse> getAllEmpleados() {
        log.info("buscando todos los empleados");
        List<Empleados> empleadosDb = (List<Empleados>) repository.findAll();
        List<EmpleadoResponse> response = buildEmpleadosAll(empleadosDb);
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public EmpleadoResponse getById(Long id) {
        Optional<Empleados> empleados = repository.findById(id);
        if (empleados.isPresent()) {
            return createEmpleadoResponse(empleados.get());
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Eliminado empleado con ID: {}", id);
        Optional<Empleados> empleados = repository.findById(id);
        if (empleados.isPresent()) {
            repository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public EmpleadoResponse updateEmpleadoById(EmpleadoRequest request, Long id) {
        log.info("actualizando empleado con ID: {}", id);
        Optional<Empleados> empleadoDb = repository.findById(id);
        if (empleadoDb.isPresent()) {
            empleadoDb.get().setIdentificationNumber(request.getIdenficationNumber());
            empleadoDb.get().setName(request.getName());
            empleadoDb.get().setAddress(request.getAddress());
            empleadoDb.get().setPhone(request.getPhone());
            empleadoDb.get().setEmail(request.getEmail());
            empleadoDb.get().setActive(request.isActive());
            repository.save(empleadoDb.get());
            return createEmpleadoResponse(empleadoDb.get());
        }
        return null;
    }

    private Empleados buildEmpleadosForSave(EmpleadoRequest empleadoRequest) {
        Empleados empleados = new Empleados();
        empleados.setIdentificationNumber(empleadoRequest.getIdenficationNumber());
        empleados.setName(empleadoRequest.getName());
        empleados.setAddress(empleadoRequest.getAddress());
        empleados.setPhone(empleadoRequest.getPhone());
        empleados.setEmail(empleadoRequest.getEmail());
        empleados.setActive(empleadoRequest.isActive());
        return empleados;
    }

    private EmpleadoResponse createEmpleadoResponse(Empleados empleados) {
        return EmpleadoResponse.builder()
                .id(empleados.getId())
                .identificationNumber(empleados.getIdentificationNumber())
                .name(empleados.getName())
                .address(empleados.getAddress())
                .phone(empleados.getPhone())
                .email(empleados.getEmail())
                .creationDate(empleados.getCreationDate())
                .isActive(empleados.isActive())
                .build();
    }

    private List<EmpleadoResponse> buildEmpleadosAll(List<Empleados> empleadosList){
        List<EmpleadoResponse> response = new ArrayList<>();
        for (Empleados empleado : empleadosList) {
            response.add(createEmpleadoResponse(empleado));
        }
        return response;
    }
}
