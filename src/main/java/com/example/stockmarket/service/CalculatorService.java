package com.example.stockmarket.service;

import com.example.stockmarket.dao.StockDao;
import com.example.stockmarket.dao.TradeDao;
import com.example.stockmarket.model.PreferredStock;
import com.example.stockmarket.model.Stock;
import com.example.stockmarket.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class CalculatorService {

    @Autowired
    private StockDao stockDao;

    @Autowired
    private TradeDao tradeDao;

    public BigDecimal calculateDividendYield(String stock, BigDecimal price) {
        Stock st = stockDao.getStock(stock);
        if (st instanceof PreferredStock) {
            // preferred
            BigDecimal top = BigDecimal.valueOf(((PreferredStock) st).getFixedDividend()).multiply(BigDecimal.valueOf(st.getParValue()));
            return top.divide(price, 2, RoundingMode.HALF_EVEN);
        }
        // common
        return BigDecimal.valueOf(st.getLastDividend()).divide(price, 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calculatePeRatio(String stock, BigDecimal price) {
        Stock st = stockDao.getStock(stock);
        if (st.getLastDividend().equals(0)) {
            return BigDecimal.ZERO;
        }
        return price.divide(BigDecimal.valueOf(stockDao.getStock(stock).getLastDividend()), 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calculateVolumeWeightedStockPrice(String stock, Integer lastMinutes) {
        // will throw 404 if the stock is not in the db
        stockDao.getStock(stock);
        List<Trade> trades = tradeDao
                .getTradesForStockAndTimeFrame(stock, ZonedDateTime.now().minusMinutes(lastMinutes), ZonedDateTime.now());

        BigDecimal topSigma = BigDecimal.ZERO;
        Integer bottomSigma = 0;

        for (Trade trade : trades) {
            BigDecimal tradeResult = trade.getPrice().multiply(BigDecimal.valueOf(trade.getQuantityOfShares()));
            topSigma = topSigma.add(tradeResult);

            bottomSigma += trade.getQuantityOfShares();
        }

        if (bottomSigma.equals(0)) {
            return BigDecimal.ZERO;
        }

        return topSigma
                .divide(BigDecimal.valueOf(bottomSigma), 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calculateAllShareIndex() {
        List<Trade> trades = tradeDao.getAllTrades();

        if (trades.size() == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal root = BigDecimal.ONE;

        for (Trade trade : trades) {
            root = root.multiply(trade.getPrice());
        }

        return BigDecimal.valueOf(Math.pow(root.doubleValue(), 1.0 / trades.size())).setScale(2, RoundingMode.HALF_EVEN);
    }
}
