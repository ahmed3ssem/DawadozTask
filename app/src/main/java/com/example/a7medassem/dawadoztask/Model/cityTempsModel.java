package com.example.a7medassem.dawadoztask.Model;

public class cityTempsModel {

    private String temp , date ;

    public cityTempsModel(String temp, String date) {
        this.temp = temp;
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
