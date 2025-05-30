package proyect_u_inventory.transformation.company.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "entrega_detalle")
public class EntregaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_entrega", nullable = false)
    private Entrega entrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Column(name = "cantidad")
    private int amount;

    @Column(name = "fecha_creacion")
    private LocalDate createDate;

    @PrePersist
    protected void prePersist() {
        this.createDate = LocalDate.now();
    }
}
