package com.veloz.lacarreta.lacarretaveloz;

public class PaymentMethodModel {

    public String tarjeta;
    public String cvs;
    public String exp;
    public String titular;

    public PaymentMethodModel(){

    };

    public PaymentMethodModel(String notarjeta, String cvs, String expdate, String titular) {
        this.tarjeta = notarjeta;
        this.cvs = cvs;
        this.exp= expdate;
        this.titular = titular;
    }

    public String getNotarjeta() {
        try{
            String s = "xxxx-xxxx-xxxx-"+this.tarjeta.substring(15);
            return s;
        }catch(StringIndexOutOfBoundsException s){
            return this.tarjeta;
        }

    }

    public String getCvs() {
        return cvs;
    }

    public String getExpdate() {
        return exp;
    }

    public String geteTitular() {
        return titular;
    }

    public void setNotarjeta(String notarjeta) {
        this.tarjeta = notarjeta;
    }

    public void setCvs(String cvs) {
        this.cvs = cvs;
    }

    public void setExpdate(String expdate) {
        this.exp= expdate;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
}
