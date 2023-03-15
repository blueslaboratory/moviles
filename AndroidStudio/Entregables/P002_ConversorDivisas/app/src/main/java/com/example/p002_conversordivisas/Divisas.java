package com.example.p002_conversordivisas;

import java.util.ArrayList;

public class Divisas {

    String name, codigo, value;

    // Constructor: ALT + INSERT
    // boton derecho del mouse --> Generate
    public Divisas(String name, String codigo, String value) {
        this.name = name;
        this.codigo = codigo;
        this.value = value;
    }


    // Getters: ALT + INSERT
    public String getName() {
        return name;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getValue() {
        return value;
    }


    // Setters: ALT + INSERT
    public void setName(String name) {
        this.name = name;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setValue(String value) {
        this.value = value;
    }

}