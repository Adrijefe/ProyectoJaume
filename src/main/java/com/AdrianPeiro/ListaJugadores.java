package com.AdrianPeiro;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ListaJugadores {

    @JsonProperty("jugadores")
    private List<Jugadores> jugadores;

    public ListaJugadores() {}

    public ListaJugadores(List<Jugadores> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Jugadores> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugadores> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        return "JugadoresList{" +
                "jugadores=" + jugadores +
                '}';
    }
}
