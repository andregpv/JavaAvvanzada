package com.actividad1.actividadSpringBoot.controller;

import com.actividad1.actividadSpringBoot.model.Triangulo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TrianguloController {

    @GetMapping("/")
    public String mostrarFormulario() {
        return "index";
    }

    @PostMapping("/calcular")
    public String calcular(@RequestParam double base,
                           @RequestParam double altura,
                           Model model) {

        Triangulo triangulo = new Triangulo(base, altura);
        model.addAttribute("base", base);
        model.addAttribute("altura", altura);
        model.addAttribute("area", triangulo.calcularArea());
        model.addAttribute("perimetro", triangulo.calcularPerimetro());
        return "resultado"; // apunta a templates/resultado.html
    }
}
