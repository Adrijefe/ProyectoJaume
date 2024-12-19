package com.AdrianPeiro;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Jugadores {
    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("pais")
    private String pais;

    @JsonProperty("titulos_grand_slam")
    private int titulosGrandSlam;

    @JsonProperty("anos_actividad")
    private String anosActividad;

    @JsonProperty("Imagen")
    private String imagen;


    public Jugadores() {}

    public Jugadores(String nombre, String pais, int titulosGrandSlam, String anosActividad, String imagen) {
        this.nombre = nombre;
        this.pais = pais;
        this.titulosGrandSlam = titulosGrandSlam;
        this.anosActividad = anosActividad;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getTitulosGrandSlam() {
        return titulosGrandSlam;
    }

    public void setTitulosGrandSlam(int titulosGrandSlam) {
        this.titulosGrandSlam = titulosGrandSlam;
    }

    public String getAnosActividad() {
        return anosActividad;
    }

    public void setAnosActividad(String anosActividad) {
        this.anosActividad = anosActividad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", titulosGrandSlam=" + titulosGrandSlam +
                ", anosActividad='" + anosActividad + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
