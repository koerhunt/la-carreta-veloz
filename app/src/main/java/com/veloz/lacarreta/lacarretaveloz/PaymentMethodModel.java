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
        try{
            String s = "xxxx-xxxx-xxxx-"+this.notarjeta.substring(15);
            return s;
        }catch(StringIndexOutOfBoundsException s){
            return this.notarjeta;
        }

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

    public void setNotarjeta(String notarjeta) {
        this.notarjeta = notarjeta;
    }

    public void setCvs(String cvs) {
        this.cvs = cvs;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
}
