package com.example.mammamia.model;

public class Constant {

    int image;
    String title;
    String ingredientes;
    String composicion;


    // CONSTRUCTOR
    // ALT + INS
    public Constant(int image, String title, String ingredientes, String composicion) {
        this.image = image;
        this.title = title;
        this.ingredientes = ingredientes;
        this.composicion = composicion;
    }


    // GETTERS
    // ALT + INS
    public int getImage() {
        return image;
    }
    public String getTitle() {
        return title;
    }
    public String getIngredientes() {
        return ingredientes;
    }
    public String getElaboracion() {
        return composicion;
    }


    // SETTERS
    // ALT + INS
    public void setImage(int image) {
        this.image = image;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }
    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }
}