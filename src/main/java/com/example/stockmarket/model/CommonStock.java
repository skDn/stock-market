package com.example.stockmarket.model;

import javax.validation.constraints.NotNull;

public class CommonStock extends Stock {

    public CommonStock() {
        super(null, StockType.COMMON, null, null);
    }

    public CommonStock(@NotNull String symbol, @NotNull Integer lastDividend, @NotNull Integer parValue) {
        super(symbol, StockType.COMMON, lastDividend, parValue);
    }
}
