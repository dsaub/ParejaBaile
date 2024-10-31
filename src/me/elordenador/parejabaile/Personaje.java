package me.elordenador.parejabaile;

public class Personaje {
    private final String nombre;
    private final Universo universo;

    public Personaje(String nombre, Universo universo) {
        this.nombre = nombre;
        this.universo = universo;
    }

    public String getNombre() {
        return nombre;
    }

    public Universo getUniverso() {
        return universo;
    }
}
