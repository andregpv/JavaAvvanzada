package com.imc.controller;

import com.imc.model.Usuario;
import com.imc.service.UsuarioService;
import com.imc.service.IMCService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaginasController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IMCService imcService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registroPost(
            @RequestParam String nombreCompleto,
            @RequestParam Integer edad,
            @RequestParam String sexo,
            @RequestParam Double estatura,
            @RequestParam String usuario,
            @RequestParam String contrasena,
            Model model) {
        try {
            usuarioService.registrar(nombreCompleto, edad, sexo, estatura, usuario, contrasena);
            model.addAttribute("mensaje", "Registro exitoso. Ahora inicia sesión");
            return "login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "registro";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(
            @RequestParam String usuario,
            @RequestParam String contrasena,
            HttpSession session,
            Model model) {
        try {
            Usuario u = usuarioService.login(usuario, contrasena);
            session.setAttribute("usuarioId", u.getId());
            session.setAttribute("nombreUsuario", u.getUsuario());
            session.setAttribute("nombreCompleto", u.getNombreCompleto());
            session.setAttribute("estatura", u.getEstatura());
            return "redirect:/dashboard";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        if (usuarioId == null) {
            return "redirect:/login";
        }
        model.addAttribute("historial", imcService.obtenerHistorial(usuarioId));
        return "dashboard";
    }

    @PostMapping("/calcular")
    public String calcular(
            @RequestParam Double peso,
            HttpSession session,
            Model model) {
        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        if (usuarioId == null) {
            return "redirect:/login";
        }

        try {
            Double estatura = (Double) session.getAttribute("estatura");
            var calculo = imcService.calcularIMC(usuarioId, peso, estatura);
            model.addAttribute("calculo", calculo);
            model.addAttribute("historial", imcService.obtenerHistorial(usuarioId));
            return "dashboard";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("historial", imcService.obtenerHistorial(usuarioId));
            return "dashboard";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
