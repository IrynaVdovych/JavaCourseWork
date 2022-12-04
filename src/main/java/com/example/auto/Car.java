package com.example.auto;

public class Car extends BaseAuto{
    public Car(String brand, String model, int yearOfRelease, String regNumber, double initialPrice, double uof, int avgVelocity) {
        super(brand, model, yearOfRelease, regNumber, initialPrice, uof, avgVelocity);
        this.setAmortCoef(0.9);
    }
    @Override
    public String toString() {
        return "Car: " + super.toString();
    }

    @Override
    public String getType() {
        return "CAR";
    }
}
