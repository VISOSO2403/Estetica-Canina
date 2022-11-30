package com.example.esteticacanina.model;

public class User {
    String Nombre;
    public User(){}

    public User(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
