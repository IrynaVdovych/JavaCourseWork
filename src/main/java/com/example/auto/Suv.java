package com.example.auto;

public class Suv extends BaseAuto{
    public Suv(String brand, String model, int yearOfRelease, String regNumber, double initialPrice, double uof, int avgVelocity) {
        super(brand, model, yearOfRelease, regNumber, initialPrice, uof, avgVelocity);
        this.setAmortCoef(0.85);
    }
    @Override
    public String toString() {
        return "SUV: " + super.toString();
    }

    @Override
    public String getType() {
        return "SUV";
    }
}
