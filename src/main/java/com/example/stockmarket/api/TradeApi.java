package com.example.stockmarket.api;

import com.example.stockmarket.model.Trade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api
@RequestMapping("/api/trade")
public interface TradeApi {

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully stored the trade"),
            @ApiResponse(code = 400, message = "Input validation failed")
    })
    @ApiOperation(value = "Stores a trade")
    @PostMapping
    void storeTrade(@Valid @RequestBody Trade trade);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved trades"),
    })
    @ApiOperation(value = "Returns all trades")
    @GetMapping
    List<Trade> getAllTrades();

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully generated trades"),
    })
    @ApiOperation(value = "Generates a number of random trades for given number of minutes before current time")
    @PutMapping("/generate/{amount}/{lastMinutes}")
    void generateRandomTrades(@PathVariable Integer amount, @PathVariable Integer lastMinutes);

}
