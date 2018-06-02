package com.veloz.lacarreta.lacarretaveloz;

public class PaymentMethodModel {

    public String notarjeta;
    public String cvs;
    public String expdate;
    public String titular;

    public PaymentMethodModel(){

    };

    public PaymentMethodModel(String notarjeta, String cvs, String expdate, String titular) {
        this.notarjeta = notarjeta;
        this.cvs = cvs;
        this.expdate = expdate;
        this.titular = titular;
    }

    public String getNotarjeta() {
        return notarjeta;
    }

    public String getCvs() {
        return cvs;
    }

    public String getExpdate() {
        return expdate;
    }

    public String getTitular() {
        return titular;
    }
}
