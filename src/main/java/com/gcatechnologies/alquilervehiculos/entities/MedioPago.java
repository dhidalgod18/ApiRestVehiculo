package com.gcatechnologies.alquilervehiculos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedioPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedioPago;

    private String nombre;

    private String descripcion;










}
