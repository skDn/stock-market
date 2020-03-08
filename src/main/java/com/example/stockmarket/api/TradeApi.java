package com.example.stockmarket.api;

import com.example.stockmarket.model.Trade;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api
@RequestMapping("/api/trade")
public interface TradeApi {

    @PostMapping
    void storeTrade(@Valid @RequestBody Trade trade);

    @GetMapping
    List<Trade> getAllTrades();

    @PutMapping("/generate/{amount}/{lastMinutes}")
    void generateRandomTrades(@PathVariable Integer amount, @PathVariable Integer lastMinutes);

}
