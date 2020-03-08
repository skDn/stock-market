package com.example.stockmarket.api;

import com.example.stockmarket.model.CalculationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Returns the result of a calculation for dividend yield of a given stock and its price")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully calculated"),
            @ApiResponse(code = 404, message = "The requested stock is not found"),
            @ApiResponse(code = 400, message = "Input validation failed")
    })
    @GetMapping("dividendYield/{stock}/{price}")
    CalculationResponse calculateDividendYield(@PathVariable String stock, @PathVariable @Min(value = 0, message = "price cannot be negative") BigDecimal price);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully calculated"),
            @ApiResponse(code = 404, message = "The requested stock is not found"),
            @ApiResponse(code = 400, message = "Input validation failed")
    })
    @ApiOperation(value = "Returns the result of a calculation for P/E Ratio of a given stock and its price")
    @GetMapping("peRatio/{stock}/{price}")
    CalculationResponse calculatePeRatio(@PathVariable String stock, @PathVariable @Min(value = 0, message = "price cannot be negative") BigDecimal price);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully calculated"),
            @ApiResponse(code = 404, message = "The requested stock is not found"),
            @ApiResponse(code = 400, message = "Input validation failed")
    })
    @ApiOperation(value = "Returns the result of a calculation for the volume weighted stock price of all trades for a given stock and given last amount of minutes")
    @GetMapping("volumeWeightedStockPrice/{stock}/{lastMinutes}")
    CalculationResponse calculateVolumeWeightedStockPrice(@PathVariable String stock, @PathVariable @Min(value = 0, message = "last minutes cannot be negative") Integer lastMinutes);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully calculated")
    })
    @ApiOperation(value = "Returns the result of a calculation for the all share index taking into account all traded shares and their prices")
    @GetMapping("allShareIndex")
    CalculationResponse calculateAllShareIndex();
}
