package com.actividad1.actividadSpringBoot.controller;

import com.actividad1.actividadSpringBoot.model.Triangulo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TrianguloController {

    @GetMapping("/")
    public String index(HttpSession session,
                        @CookieValue(value = "base", defaultValue = "") String base,
                        @CookieValue(value = "altura", defaultValue = "") String altura,
                        @CookieValue(value = "area", defaultValue = "") String area,
                        @CookieValue(value = "perimetro", defaultValue = "") String perimetro,
                        Model model) {

        String nombre = (String) session.getAttribute("nombre");
        model.addAttribute("nombre", nombre);

        if (!base.isEmpty()) {
            model.addAttribute("ultimo", true);
            model.addAttribute("base", base);
            model.addAttribute("altura", altura);
            model.addAttribute("area", area);
            model.addAttribute("perimetro", perimetro);
        } else {
            model.addAttribute("ultimo", false);
        }

        return "index";
    }

    @PostMapping("/guardar-nombre")
    public String guardarNombre(@RequestParam("nombre") String nombre, HttpSession session) {
        session.setAttribute("nombre", nombre);
        return "redirect:/";
    }

    @PostMapping("/calcular")
    public String calcular(@RequestParam double base,
                           @RequestParam double altura,
                           HttpSession session,
                           HttpServletResponse response,
                           Model model) {

        Triangulo t = new Triangulo(base, altura);

        response.addCookie(new Cookie("base", String.valueOf(base)));
        response.addCookie(new Cookie("altura", String.valueOf(altura)));
        response.addCookie(new Cookie("area", String.valueOf(t.getArea())));
        response.addCookie(new Cookie("perimetro", String.valueOf(t.getPerimetro())));

        String nombre = (String) session.getAttribute("nombre");
        model.addAttribute("nombre", nombre);
        model.addAttribute("triangulo", t);

        return "resultado";
    }
}
