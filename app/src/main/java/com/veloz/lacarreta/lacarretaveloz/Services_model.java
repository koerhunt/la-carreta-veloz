package com.veloz.lacarreta.lacarretaveloz;

public class Services_model {
    boolean estado;
    String costo;
    String distancia;
    String fecha;
    String conductor;

    public Services_model(boolean estado, String costo, String distancia, String fecha, String conductor) {
        this.estado = estado;
        this.costo = costo;
        this.distancia = distancia;
        this.fecha = fecha;
        this.conductor = conductor;
    }

    public Services_model() {

    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getConductor() {

        return conductor;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getCosto() {
        return costo;
    }

    public String getDistancia() {
        return distancia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }














}
