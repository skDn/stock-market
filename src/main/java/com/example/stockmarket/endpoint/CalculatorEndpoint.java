package com.example.stockmarket.endpoint;

import com.example.stockmarket.api.CalculatorApi;
import com.example.stockmarket.model.CalculationResponse;
import com.example.stockmarket.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CalculatorEndpoint implements CalculatorApi {

    @Autowired
    private CalculatorService calculatorService;

    @Override
    public CalculationResponse calculateDividendYield(String stock, BigDecimal price) {
        return new CalculationResponse(calculatorService.calculateDividendYield(stock, price));
    }

    @Override
    public CalculationResponse calculatePeRatio(String stock, BigDecimal price) {
        return new CalculationResponse(calculatorService.calculatePeRatio(stock, price));
    }

    @Override
    public CalculationResponse calculateVolumeWeightedStockPrice(String stock, Integer lastMinutes) {
        return new CalculationResponse(calculatorService.calculateVolumeWeightedStockPrice(stock, lastMinutes));
    }

    @Override
    public CalculationResponse calculateAllShareIndex() {
        return new CalculationResponse(calculatorService.calculateAllShareIndex());
    }

}
