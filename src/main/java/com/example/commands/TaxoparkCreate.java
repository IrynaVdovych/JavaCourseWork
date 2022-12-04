package com.example.commands;

import com.example.taxopark.Taxopark;

public class TaxoparkCreate implements Command{
    private Taxopark tp = null;

    public TaxoparkCreate(Taxopark tp) {
        this.tp = tp;
    }

    @Override
    public void execute() {
        tp.create();
    }

    @Override
    public void execute(String[] params) {
        tp.create(params);
    }
}
