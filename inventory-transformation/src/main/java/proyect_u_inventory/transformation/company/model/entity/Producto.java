package proyect_u_inventory.transformation.company.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * entidad de productos de base de datos
 */
@Data
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "descripcion")
    private String desciption;

    @Column(name = "tipo")
    private String status;

    private int stock;

    @Column(name = "fecha_creacion")
    private LocalDate creationDate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "picture", columnDefinition = "longblob")
    private byte[] picture;


    @PrePersist
    private void prePersist() {
        this.creationDate = LocalDate.now();
    }

}
