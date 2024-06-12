package com.example.delicias.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name="pedidos")
public class Pedidos {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_pedido")
    private Long id;

    @Column(name="fecha_pedido")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPedido;
    @Column(name="direcion_entrega")
    private String direccionEntrega;
    @Column(name="estado_producto")
    private String estadoProducto;
    @Column(name="cantidad")
    private Integer cantidad;
    @Column(name="precio_unitario")
    private Integer precioUnitario;     


    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
