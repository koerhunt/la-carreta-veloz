package com.veloz.lacarreta.lacarretaveloz;

public class UserProfile {

    public String nocontrol;
    public String carrera;
    public int semestre;
    public String telefono;
    public String nombre;

    public UserProfile(){

    }

    public UserProfile(String nocontrol, String carrera, int semestre, String telefono, String nombre) {
        this.nocontrol = nocontrol;
        this.carrera = carrera;
        this.semestre = semestre;
        this.telefono = telefono;
        this.nombre = nombre;
    }
}
