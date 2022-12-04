package com.example.commands;

import com.example.taxopark.Taxopark;

public class TaxoparkSortList implements Command {
    private Taxopark tp = null;

    public TaxoparkSortList(Taxopark tp) {
        this.tp = tp;
    }

    @Override
    public void execute() {
        tp.sortList();
    }

    @Override
    public void execute(String[] params) {
        tp.sortList(params);
    }
}
