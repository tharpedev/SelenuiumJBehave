package org.qas.jbehave.example.model;

public class PhoneInfo {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private String name;
    private double price;

    public PhoneInfo(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
