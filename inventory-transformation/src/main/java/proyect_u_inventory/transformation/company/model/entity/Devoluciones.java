package proyect_u_inventory.transformation.company.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "devolciones")
public class Devoluciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devolucion")
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleados empleados;

    @Column(name = "fecha_creacion")
    private LocalDate createDate;

    @PrePersist
    protected void prePersist() {
        this.createDate = LocalDate.now();
    }
}
