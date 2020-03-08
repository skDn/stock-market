package com.example.stockmarket.dao;

import com.example.stockmarket.model.Trade;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class InMemoryTradeDao implements TradeDao {

    private Map<String, Trade> db;

    public InMemoryTradeDao() {
        db = new ConcurrentHashMap<>();
    }

    @Override
    public void storeTrade(Trade trade) {
        db.put(UUID.randomUUID().toString(), trade);
    }

    @Override
    public List<Trade> getAllTrades() {
        return new ArrayList<>(db.values());
    }

    @Override
    public List<Trade> getTradesForStockAndTimeFrame(String stock, ZonedDateTime from, ZonedDateTime to) {
        return getAllTrades().stream()
                .filter(trade -> trade.getStock().equals(stock)
                        && trade.getTimestamp().isAfter(from)
                        && trade.getTimestamp().isBefore(to))
                .collect(Collectors.toList());
    }
}
