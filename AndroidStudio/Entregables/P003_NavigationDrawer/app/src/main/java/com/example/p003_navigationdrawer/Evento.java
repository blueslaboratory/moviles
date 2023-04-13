package com.example.p003_navigationdrawer;

public class Evento {

    int codigo;
    String fecha, tipo, modulo, estado;

    // CONSTRUCTOR
    public Evento(int codigo, String fecha, String tipo, String modulo, String estado) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.tipo = tipo;
        this.modulo = modulo;
        this.estado = estado;
    }


    // GETTERS
    public int getCodigo() {return codigo; }
    public String getFecha() {
        return fecha;
    }
    public String getTipo() {
        return tipo;
    }
    public String getModulo() {
        return modulo;
    }
    public String getEstado() {
        return estado;
    }


    // SETTERS
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setModulo(String modulo) {
        this.modulo = modulo;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
