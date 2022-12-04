package com.example.auto;

public class Van extends BaseAuto{
    public Van(String brand, String model, int yearOfRelease, String regNumber, double initialPrice, double uof, int avgVelocity) {
        super(brand, model, yearOfRelease, regNumber, initialPrice, uof, avgVelocity);
        this.setAmortCoef(0.8);
    }
    @Override
    public String toString() {
        return "Van: " + super.toString();
    }

    @Override
    public String getType() {
        return "VAN";
    }
}
