package com.example.commands;

import com.example.taxopark.Taxopark;

public class TaxoparkShowList implements Command {
    private Taxopark tp = null;

    public TaxoparkShowList(Taxopark tp) {
        this.tp = tp;
    }

    @Override
    public void execute() {
        tp.showList();
    }

    @Override
    public void execute(String[] params) {
        tp.showList(params);
    }
}
