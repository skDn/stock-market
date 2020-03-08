package com.example.stockmarket.model;

import java.math.BigDecimal;

public class CalculationResponse {

    private BigDecimal result;

    public CalculationResponse(BigDecimal result) {
        this.result = result;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
