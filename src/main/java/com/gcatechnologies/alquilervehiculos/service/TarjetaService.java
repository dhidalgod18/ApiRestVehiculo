package com.gcatechnologies.alquilervehiculos.service;

import com.gcatechnologies.alquilervehiculos.entities.Tarjeta;
import com.gcatechnologies.alquilervehiculos.entities.Usuario;
import com.gcatechnologies.alquilervehiculos.entities.Vehiculo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TarjetaService {
    Tarjeta agregarTarjeta(Tarjeta tarjeta);
    List<Tarjeta> obtenerTarjeta();

    boolean eliminarTarjeta(int idTarjeta);

    boolean editarTarjeta(Tarjeta tarjeta);

}
