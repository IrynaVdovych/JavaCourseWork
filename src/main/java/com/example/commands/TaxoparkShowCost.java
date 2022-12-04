package com.example.commands;

import com.example.taxopark.Taxopark;

public class TaxoparkShowCost implements Command{
    private Taxopark tp = null;

    public TaxoparkShowCost(Taxopark tp) {
        this.tp = tp;
    }

    @Override
    public void execute() {
        tp.showCost();
    }

    @Override
    public void execute(String[] params) {
        tp.showCost(params);
    }
}
