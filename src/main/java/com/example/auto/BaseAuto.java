package com.example.auto;

import java.time.Year;

public class BaseAuto {
    private String brand;
    private String model;
    private int yearOfRelease;
    private String regNumber;
    private double initialPrice;
    private double uof;
    private int avgVelocity;

    private double amortCoef = 0.95;



    public BaseAuto(String brand, String model, int yearOfRelease, String regNumber, double initialPrice, double uof, int avgVelocity) {
        this.brand = brand;
        this.model = model;
        this.yearOfRelease = yearOfRelease;
        this.regNumber = regNumber;
        this.initialPrice = initialPrice;
        this.uof = uof;
        this.avgVelocity = avgVelocity;
    }

    public void setAmortCoef(double amortCoef) {
        this.amortCoef = amortCoef;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public double getUof() {
        return uof;
    }

    public int getAvgVelocity() {
        return avgVelocity;
    }

    public String getType() {
        return "BASEAUTO";
    }

    public String getRegNumber() {
        return regNumber;
    }

    @Override
    public String toString() {
        return brand + " " + model + " (" + regNumber + ")";
    }

    public int getAge() {
        Year year = Year.now();
        return year.getValue() - this.yearOfRelease;
    }

    public double getPrice() {
        int age = this.getAge();
        double price = this.initialPrice * Math.pow(this.amortCoef, age);
        price = (double) Math.round(price * 10) / 10;
        return price;
    }

    public boolean velocityBetweenRange(int minVel, int maxVel) {
        return (minVel <= this.avgVelocity && this.avgVelocity <= maxVel);
    }

}
