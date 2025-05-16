package proyect_u_inventory.transformation.company.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * datos de los empleados contratados
 */
@Data
@Entity
@Table(name = "empleado")
public class Empleados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificacion")
    private String identificationNumber;

    @Column(name = "nombre")
    private String name;

    @Column(name = "direccion")
    private String address;

    @Column(name = "telefono")
    private String phone;

    @Column(name = "correo")
    private String email;

    @Column(name = "fecha_creacion")
    private LocalDate creationDate;

    @Column(name = "activo")
    private boolean isActive;

    @PrePersist
    protected void prePersit(){
        this.creationDate = LocalDate.now();
    }
}
