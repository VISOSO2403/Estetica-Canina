package com.example.esteticacanina.model;

public class Pet {

    String Edad, Nombre, Peso, Sexo, Tipo, tamano;
    public Pet(){}

    public Pet(String edad, String nombre, String peso, String sexo, String tipo, String tamano) {
        Edad = edad;
        Nombre = nombre;
        Peso = peso;
        Sexo = sexo;
        Tipo = tipo;
        this.tamano = tamano;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }
}
