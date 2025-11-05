package com.imc.service;

import com.imc.model.CalculoIMC;
import com.imc.repository.CalculoIMCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IMCService {

    @Autowired
    private CalculoIMCRepository calculoRepository;

    public CalculoIMC calcularIMC(Integer usuarioId, Double peso, Double estatura) {
        // Validar peso
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor a 0");
        }

        // Calcular IMC
        Double imc = peso / (estatura * estatura);
        String clasificacion = clasificarIMC(imc);

        CalculoIMC calculo = new CalculoIMC(usuarioId, peso, imc, clasificacion);
        return calculoRepository.guardar(calculo);
    }

    public String clasificarIMC(Double imc) {
        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc >= 18.5 && imc < 25) {
            return "Peso normal";
        } else if (imc >= 25 && imc < 30) {
            return "Sobrepeso";
        } else {
            return "Obesidad";
        }
    }

    public List<CalculoIMC> obtenerHistorial(Integer usuarioId) {
        return calculoRepository.obtenerPorUsuario(usuarioId);
    }
}
