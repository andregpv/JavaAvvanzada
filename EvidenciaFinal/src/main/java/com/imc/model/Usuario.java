package com.imc.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private Integer id;
    private String nombreCompleto;
    private Integer edad;
    private String sexo;
    private Double estatura;
    private String usuario;
    private String contrasena;

    public Usuario() {}

    public Usuario(String nombreCompleto, Integer edad, String sexo, Double estatura, String usuario, String contrasena) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.sexo = sexo;
        this.estatura = estatura;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public Double getEstatura() { return estatura; }
    public void setEstatura(Double estatura) { this.estatura = estatura; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}
