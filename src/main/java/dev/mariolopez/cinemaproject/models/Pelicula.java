package dev.mariolopez.cinemaproject.models;

import java.util.UUID;

public class Pelicula {
    private String dni;
    private String nombre;
    private String genero;
    private String tipoAudiencia;

    public Pelicula(String nombre, String genero, String tipoAudiencia) {
        this.dni = UUID.randomUUID().toString(); // Genera un identificador único
        this.nombre = nombre;
        this.genero = genero;
        this.tipoAudiencia = tipoAudiencia;
    }

    // Getters
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getTipoAudiencia() {
        return tipoAudiencia;
    }
}
