package com.imc.repository;

import com.imc.model.Usuario;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UsuarioRepository {
    private Map<Integer, Usuario> usuarios = new HashMap<>();
    private int idCounter = 1;

    public Usuario registrar(Usuario usuario) {
        usuario.setId(idCounter++);
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    public Usuario obtenerPorUsuario(String nombreUsuario) {
        return usuarios.values().stream()
                .filter(u -> u.getUsuario().equals(nombreUsuario))
                .findFirst()
                .orElse(null);
    }

    public boolean usuarioExiste(String nombreUsuario) {
        return usuarios.values().stream()
                .anyMatch(u -> u.getUsuario().equals(nombreUsuario));
    }

    public Usuario obtenerPorId(Integer id) {
        return usuarios.get(id);
    }
}
