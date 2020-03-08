package com.example.stockmarket.service;

import com.example.stockmarket.dao.StockDao;
import com.example.stockmarket.dao.TradeDao;
import com.example.stockmarket.model.SellIndicator;
import com.example.stockmarket.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class TradeService {

    @Autowired
    private TradeDao tradeDao;

    @Autowired
    private StockDao stockDao;

    public void storeTrade(Trade trade) {
        stockDao.checkIfStockValid(trade.getStock());
        tradeDao.storeTrade(trade);
    }

    public List<Trade> getAllTrades() {
        return tradeDao.getAllTrades();
    }

    public void generateRandomTrades(Integer amount, Integer lastMinutes) {
        for (int i = 0; i < amount; i++) {
            // timestamp
            ZonedDateTime timestamp = ZonedDateTime.now()
                    .minusSeconds(ThreadLocalRandom.current().nextInt(0, lastMinutes * 60));
            // stock
            String[] stockKeys = stockDao.getAllStockKeys();
            String stock = stockKeys[ThreadLocalRandom.current().nextInt(0, stockKeys.length)];

            // quantity
            Integer quantity = ThreadLocalRandom.current().nextInt(1, 100);

            // price
            BigDecimal price = new BigDecimal(
                    BigInteger.valueOf(ThreadLocalRandom.current().nextInt(1, 10000)),
                    2);


            storeTrade(new Trade(timestamp, stock, quantity, SellIndicator.randomSellIndicator(), price));
        }
    }
}
