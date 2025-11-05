package com.imc.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalculoIMC implements Serializable {
    private Integer id;
    private Integer usuarioId;
    private Double peso;
    private Double imc;
    private String clasificacion;
    private LocalDateTime fecha;

    public CalculoIMC() {}

    public CalculoIMC(Integer usuarioId, Double peso, Double imc, String clasificacion) {
        this.usuarioId = usuarioId;
        this.peso = peso;
        this.imc = imc;
        this.clasificacion = clasificacion;
        this.fecha = LocalDateTime.now();
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Double getImc() { return imc; }
    public void setImc(Double imc) { this.imc = imc; }

    public String getClasificacion() { return clasificacion; }
    public void setClasificacion(String clasificacion) { this.clasificacion = clasificacion; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getFechaFormato() {
        if (fecha == null) return "";
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
