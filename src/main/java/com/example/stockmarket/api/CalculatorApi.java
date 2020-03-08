package com.example.stockmarket.api;

import com.example.stockmarket.model.CalculationResponse;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Api
@RequestMapping("/api/calculate")
@Validated
public interface CalculatorApi {

    @GetMapping("dividendYield/{stock}/{price}")
    CalculationResponse calculateDividendYield(@PathVariable String stock, @PathVariable @Min(value = 0, message = "price cannot be negative") BigDecimal price);

    @GetMapping("peRatio/{stock}/{price}")
    CalculationResponse calculatePeRatio(@PathVariable String stock, @PathVariable @Min(value = 0, message = "price cannot be negative") BigDecimal price);

    @GetMapping("volumeWeightedStockPrice/{stock}/{lastMinutes}")
    CalculationResponse calculateVolumeWeightedStockPrice(@PathVariable String stock, @PathVariable @Min(value = 0, message = "last minutes cannot be negative") Integer lastMinutes);

    @GetMapping("allShareIndex")
    CalculationResponse calculateAllShareIndex();
}
