package com.example.e010_firebase;

public class Alumno {

    private String nombre, documento;
    private Integer  edad;

    //Importante: Cada clase personalizada debe tener un constructor público que no acepte argumentos.
    // Además, la clase debe incluir un método get público para cada propiedad.
    public Alumno() {}

    public Alumno(String nombre, String documento, Integer edad) {
        nombre = nombre;
        documento = documento;
        edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public Integer getEdad() {
        return edad;
    }
}
