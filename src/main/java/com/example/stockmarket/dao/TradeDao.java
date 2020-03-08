package com.example.stockmarket.dao;

import com.example.stockmarket.model.Trade;

import java.time.ZonedDateTime;
import java.util.List;

public interface TradeDao {

    void storeTrade(Trade trade);

    List<Trade> getAllTrades();

    List<Trade> getTradesForStockAndTimeFrame(String stock, ZonedDateTime from, ZonedDateTime to);
}
