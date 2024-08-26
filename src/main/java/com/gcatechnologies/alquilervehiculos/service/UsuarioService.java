package com.gcatechnologies.alquilervehiculos.service;
import com.gcatechnologies.alquilervehiculos.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    Usuario agregarUsuario(Usuario usuario);
    List<Usuario> obtenerUsuarios();

    boolean eliminarUsuario(Long idUsuario);

    Usuario buscarUsuario(Long idUsuario);

}
