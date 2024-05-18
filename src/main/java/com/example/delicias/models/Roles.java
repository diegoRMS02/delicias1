package com.example.delicias.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter @Setter
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_roles")
    private Long id;
    @Column(name="roles")
    private String roles;

    
    @ManyToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private Set<Usuario> usuarios = new HashSet<>();
}
