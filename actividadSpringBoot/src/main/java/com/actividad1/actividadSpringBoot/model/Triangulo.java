package com.actividad1.actividadSpringBoot.model;

public class Triangulo {
    private double base;
    private double altura;
    private double area;
    private double perimetro;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
        this.area = (base * altura) / 2;
        this.perimetro = base * 3;
    }

    public double getBase() { return base; }
    public double getAltura() { return altura; }
    public double getArea() { return area; }
    public double getPerimetro() { return perimetro; }
}
