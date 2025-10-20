package com.actividad1.actividadSpringBoot.model;

public class Triangulo {
    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double calcularArea() {
        return (base * altura) / 2;
    }

    public double calcularPerimetro() {
        return 3 * base;
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }
}
