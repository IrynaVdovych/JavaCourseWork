package com.example.cw2.model;

import javafx.beans.property.*;

public class BaseAutoFX {
    private final StringProperty type;
    private final StringProperty brand;
    private final StringProperty model;
    private final IntegerProperty yearOfRelease;
    private final StringProperty regNumber;
    private final IntegerProperty initialPrice;
    private final DoubleProperty uof;
    private final IntegerProperty avgVelocity;

    public BaseAutoFX() {
        this(null, null, null, 0, null, 0, 0, 0);
    }

    public BaseAutoFX(String type, String brand, String model, int yearOfRelease, String regNumber, int initialPrice, double uof, int avgVelocity) {
        this.type = new SimpleStringProperty(type);
        this.brand = new SimpleStringProperty(brand);
        this.model = new SimpleStringProperty(model);
        this.yearOfRelease = new SimpleIntegerProperty(yearOfRelease);
        this.regNumber = new SimpleStringProperty(regNumber);
        this.initialPrice = new SimpleIntegerProperty(initialPrice);
        this.uof = new SimpleDoubleProperty(uof);
        this.avgVelocity = new SimpleIntegerProperty(avgVelocity);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getBrand() {
        return brand.get();
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public int getYearOfRelease() {
        return yearOfRelease.get();
    }

    public IntegerProperty yearOfReleaseProperty() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease.set(yearOfRelease);
    }

    public String getRegNumber() {
        return regNumber.get();
    }

    public StringProperty regNumberProperty() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber.set(regNumber);
    }

    public int getInitialPrice() {
        return initialPrice.get();
    }

    public IntegerProperty initialPriceProperty() {
        return initialPrice;
    }

    public void setInitialPrice(int initialPrice) {
        this.initialPrice.set(initialPrice);
    }

    public double getUof() {
        return uof.get();
    }

    public DoubleProperty uofProperty() {
        return uof;
    }

    public void setUof(double uof) {
        this.uof.set(uof);
    }

    public int getAvgVelocity() {
        return avgVelocity.get();
    }

    public IntegerProperty avgVelocityProperty() {
        return avgVelocity;
    }

    public void setAvgVelocity(int avgVelocity) {
        this.avgVelocity.set(avgVelocity);
    }
}
