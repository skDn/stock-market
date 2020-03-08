package com.example.stockmarket.model;

import javax.validation.constraints.NotNull;

public abstract class Stock {

    @NotNull
    private String symbol;

    @NotNull
    private StockType stockType;

    @NotNull
    private Integer lastDividend;

    @NotNull
    private Integer parValue;

    public Stock() {
    }

    public Stock(@NotNull String symbol, @NotNull StockType stockType, @NotNull Integer lastDividend, @NotNull Integer parValue) {
        this.symbol = symbol;
        this.stockType = stockType;
        this.lastDividend = lastDividend;
        this.parValue = parValue;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public StockType getStockType() {
        return stockType;
    }

    public Integer getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(Integer lastDividend) {
        this.lastDividend = lastDividend;
    }

    public Integer getParValue() {
        return parValue;
    }

    public void setParValue(Integer parValue) {
        this.parValue = parValue;
    }
}
