package com.example.stockmarket.endpoint;

import com.example.stockmarket.api.TradeApi;
import com.example.stockmarket.model.Trade;
import com.example.stockmarket.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeEndpoint implements TradeApi {

    @Autowired
    private TradeService tradeService;

    @Override
    public void storeTrade(Trade trade) {
        tradeService.storeTrade(trade);
    }

    @Override
    public List<Trade> getAllTrades() {
        return tradeService.getAllTrades();
    }

    @Override
    public void generateRandomTrades(Integer amount, Integer lastMinutes) {
        tradeService.generateRandomTrades(amount, lastMinutes);
    }

}
