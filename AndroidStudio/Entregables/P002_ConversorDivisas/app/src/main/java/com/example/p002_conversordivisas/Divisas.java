package com.example.p002_conversordivisas;

import java.util.ArrayList;

public class Divisas {

    String name, codigo, value, simbolo;

    // Constructor: ALT + INSERT
    // boton derecho del mouse --> Generate
    public Divisas(String name, String codigo, String value, String simbolo) {
        this.name = name;
        this.codigo = codigo;
        this.value = value;
        this.simbolo = simbolo;
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
    public String getSimbolo() {
        return simbolo;
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
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

}