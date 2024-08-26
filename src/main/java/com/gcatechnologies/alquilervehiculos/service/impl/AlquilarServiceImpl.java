package com.gcatechnologies.alquilervehiculos.service.impl;
import com.gcatechnologies.alquilervehiculos.entities.Alquilar;
import com.gcatechnologies.alquilervehiculos.entities.EstadoDAO;
import com.gcatechnologies.alquilervehiculos.repository.AlquilarRepository;
import com.gcatechnologies.alquilervehiculos.service.AlquilarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlquilarServiceImpl implements AlquilarService {

    private final AlquilarRepository alquilarRepository;
    @Autowired
    public AlquilarServiceImpl(AlquilarRepository alquilarRepository) {
        this.alquilarRepository = alquilarRepository;
    }

    @Override
    public Alquilar crearRenta(Alquilar alquilar) {
        return alquilarRepository.save(alquilar);
    }

    @Override
    public List<Alquilar> obtenerRentas() {
        return alquilarRepository.findAll();
    }

    @Override
    public boolean cambiarEstado(EstadoDAO estadoDAO) {
        Optional<Alquilar> alquilar = alquilarRepository.findById(estadoDAO.getIdAlquiler());
        if (alquilar.isPresent()){
            Alquilar alquilar1 = alquilar.get();
            if (estadoDAO.getEstado().equals("Cerrado")){
                LocalDateTime fechaHoraActual = LocalDateTime.now();
                alquilar1.setFechafinal(fechaHoraActual);
            }
            alquilar1.setEstado(estadoDAO.getEstado());
            alquilarRepository.save(alquilar1);
            return true;


        }
        return false;
    }
}
