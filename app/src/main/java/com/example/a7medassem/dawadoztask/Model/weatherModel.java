package com.example.a7medassem.dawadoztask.Model;

public class weatherModel {

    private String name , temp;

    public weatherModel(String name, String temp) {
        this.name = name;
        this.temp = temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
