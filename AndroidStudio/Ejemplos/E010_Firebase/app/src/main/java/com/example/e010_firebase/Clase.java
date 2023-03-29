package com.example.e010_firebase;

public class Clase {

    private String grado;
    private Integer  numeroalumnos;
    private Long curso;

    // Importante: Cada clase personalizada debe tener un constructor público que no acepte argumentos.
    // Además, la clase debe incluir un método get público para cada propiedad.
    public Clase(){}

    public Clase(String grado, Long curso, Integer numeroalumnos) {
        this.grado = grado;
        this.curso = curso;
        this.numeroalumnos = numeroalumnos;
    }

    public Long getCurso() {
        return curso;
    }

    public Integer getNumeroalumnos() {
        return numeroalumnos;
    }

    public String getGrado() {
        return grado;
    }
}
