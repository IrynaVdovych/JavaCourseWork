package com.example.commands;

import com.example.taxopark.Taxopark;

public class TaxoparkRemoveAuto implements Command{
    private Taxopark tp = null;

    public TaxoparkRemoveAuto(Taxopark tp) {
        this.tp = tp;
    }

    @Override
    public void execute() {
        tp.removeAuto();
    }

    @Override
    public void execute(String[] params) {
        tp.removeAuto(params);
    }
}
