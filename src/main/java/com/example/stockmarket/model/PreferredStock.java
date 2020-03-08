package com.example.stockmarket.model;

import javax.validation.constraints.NotNull;

public class PreferredStock extends Stock {

    @NotNull
    private Integer fixedDividend;

    public PreferredStock() {
        super(null, StockType.PREFERRED, null, null);
    }

    public PreferredStock(@NotNull String symbol, @NotNull Integer lastDividend, @NotNull Integer parValue, @NotNull Integer fixedDividend) {
        super(symbol, StockType.PREFERRED, lastDividend, parValue);
        this.fixedDividend = fixedDividend;
    }

    public Integer getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(Integer fixedDividend) {
        this.fixedDividend = fixedDividend;
    }
}
