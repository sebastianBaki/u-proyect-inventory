package proyect_u_inventory.transformation.company.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * entidad de entrega
 *
 */
@Data
@Entity
@Table(name = "entregas")
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrega")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleados empleado;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @Column(name ="fecha_creacion")
    private LocalDate creationDate;

    @PrePersist
    protected void prePersist() {
        this.creationDate = LocalDate.now();
    }
}
