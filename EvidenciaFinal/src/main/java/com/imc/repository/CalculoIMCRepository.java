package com.imc.repository;

import com.imc.model.CalculoIMC;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CalculoIMCRepository {
    private List<CalculoIMC> calculos = new ArrayList<>();
    private int idCounter = 1;

    public CalculoIMC guardar(CalculoIMC calculo) {
        calculo.setId(idCounter++);
        calculos.add(calculo);
        return calculo;
    }

    public List<CalculoIMC> obtenerPorUsuario(Integer usuarioId) {
        return calculos.stream()
                .filter(c -> c.getUsuarioId().equals(usuarioId))
                .collect(Collectors.toList());
    }
}
