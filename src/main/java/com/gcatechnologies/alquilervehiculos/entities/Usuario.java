package com.gcatechnologies.alquilervehiculos.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String nombrePila;
    private String apellido;
    private Long cedula;

    private String correo;

    private String contrasena;

    private String rol;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedioPago> medioPagos;





}
