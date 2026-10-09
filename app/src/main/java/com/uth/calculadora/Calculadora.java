package com.uth.calculadora;

public class Calculadora {
    Double numero1;
    Double numero2;

    public Calculadora(Double numero1, Double numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public Double suma() {
        return numero1 + numero2;
    }

    public Double resta() {
        return numero1 - numero2;
    }

    public Double multiplicacion() {
        return numero1 * numero2;
    }

    public Double division() {
        return numero1 / numero2;
    }

}
