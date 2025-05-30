package proyect_u_inventory.transformation.company.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "devolucion_detalle")
public class DevolucionDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_devolucion", nullable = false)
    private Devoluciones devolucion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "fecha_creacion")
    private LocalDate createDate;

    @PrePersist
    protected void prePersist() {
        this.createDate = LocalDate.now();
    }
}
