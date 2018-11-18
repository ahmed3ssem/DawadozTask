package com.example.a7medassem.dawadoztask.Model;

public class weatherModel {

    private String name , temp , pressure;

    public weatherModel(String name, String temp , String pressure) {
        this.name = name;
        this.temp = temp;
        this.pressure = pressure;
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

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
