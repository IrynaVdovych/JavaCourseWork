package com.example.auto;

public class Truck extends BaseAuto{
    public Truck(String brand, String model, int yearOfRelease, String regNumber, double initialPrice, double uof, int avgVelocity) {
        super(brand, model, yearOfRelease, regNumber, initialPrice, uof, avgVelocity);
        this.setAmortCoef(0.75);
    }
    @Override
    public String toString() {
        return "Truck: " + super.toString();
    }

    @Override
    public String getType() {
        return "TRUCK";
    }
}
