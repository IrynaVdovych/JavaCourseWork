package com.example.commands;

import com.example.taxopark.Taxopark;

public class TaxoparkSearchAuto implements Command {
    private Taxopark tp = null;

    public TaxoparkSearchAuto(Taxopark tp) {
        this.tp = tp;
    }

    @Override
    public void execute() {
        tp.searchAuto();
    }

    @Override
    public void execute(String[] params) {
        tp.searchAuto(params);
    }
}
