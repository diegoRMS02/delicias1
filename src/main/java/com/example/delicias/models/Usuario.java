package com.example.delicias.models;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long id;
    @Column(name="nombre_usuario")
    private String nombre;  
    @Column(name="apellido_usuario")
    private String apellido;
    @Column(name="celular_usuario")
    private Long celular;
    @Column(name="correo_usuario")
    private String correo;
    @Column(name="contraseña_usuario")
    private String contrasena;


    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="fecha_registro_usuario")
    private Date fechaRegistro;


    @OneToMany(mappedBy = "usuario")
    private Set<Producto> productos = new HashSet<>(); 
      
    @ManyToMany
    @JoinTable(
        name = "rolesusuario",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_roles")
    )
    private Set<Roles> rol = new HashSet<>();


    @OneToMany(mappedBy = "usuario")
    private List<Pedidos> pedidos;

}
