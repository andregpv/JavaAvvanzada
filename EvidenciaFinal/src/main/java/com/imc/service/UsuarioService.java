package com.imc.service;

import com.imc.model.Usuario;
import com.imc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrar(String nombreCompleto, Integer edad, String sexo, Double estatura, String usuario, String contrasena) {
        // Validaciones
        if (edad < 15) {
            throw new IllegalArgumentException("La edad debe ser mayor o igual a 15 años");
        }
        
        if (estatura < 1.0 || estatura > 2.5) {
            throw new IllegalArgumentException("La estatura debe estar entre 1m y 2.5m");
        }

        if (usuarioRepository.usuarioExiste(usuario)) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        Usuario nuevoUsuario = new Usuario(nombreCompleto, edad, sexo, estatura, usuario, contrasena);
        return usuarioRepository.registrar(nuevoUsuario);
    }

    public Usuario login(String usuario, String contrasena) {
        Usuario u = usuarioRepository.obtenerPorUsuario(usuario);
        
        if (u == null) {
            throw new IllegalArgumentException("Usuario o contraseña incorrectos");
        }

        if (!u.getContrasena().equals(contrasena)) {
            throw new IllegalArgumentException("Usuario o contraseña incorrectos");
        }

        return u;
    }

    public Usuario obtenerPorId(Integer id) {
        return usuarioRepository.obtenerPorId(id);
    }
}
