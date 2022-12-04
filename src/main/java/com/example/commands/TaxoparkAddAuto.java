package com.example.commands;

import com.example.taxopark.Taxopark;

public class TaxoparkAddAuto implements Command{
    private Taxopark tp = null;

    public TaxoparkAddAuto(Taxopark tp) {
        this.tp = tp;
    }

    @Override
    public void execute() {
        tp.addAuto();
    }

    @Override
    public void execute(String[] params) {
        tp.addAuto(params);
    }
}
