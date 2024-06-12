package com.example.delicias.models;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Producto {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long id;

    @Column(name="nombre_producto")
    private String nombre;

    @Column(name="descripcion_producto")
    private String descripcion;

    @Column(name="precio_producto")
    private BigDecimal precio;

    @Column(name="imagen_producto")
    private String imagen;

    @Column(name="descuento_producto")
    private Integer descuento;
    @ManyToMany
    @JoinTable(
        name = "producto_categoria",
        joinColumns = @JoinColumn(name = "id_producto"),
        inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> categorias = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_usuario_producto")
    private Usuario usuario;


    @OneToMany(mappedBy = "producto")
    private List<Pedidos> pedidos;

    
}
