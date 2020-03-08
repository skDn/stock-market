package com.example.stockmarket.service;

import com.example.stockmarket.dao.StockDao;
import com.example.stockmarket.dao.TradeDao;
import com.example.stockmarket.model.CommonStock;
import com.example.stockmarket.model.PreferredStock;
import com.example.stockmarket.model.SellIndicator;
import com.example.stockmarket.model.Trade;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class CalculatorServiceTest {

    private final String STOCK_SYMBOL = "TEST";

    @Mock
    private TradeDao mockTradeDao;

    @Mock
    private StockDao mockStockDao;

    @InjectMocks
    private CalculatorService testCalculatorService;

    @Test
    void calculateDividendYieldCommon() {
        CommonStock commonStock = new CommonStock(STOCK_SYMBOL, 10, 100);
        when(mockStockDao.getStock(anyString())).thenReturn(commonStock);

        BigDecimal result = testCalculatorService.calculateDividendYield(STOCK_SYMBOL, BigDecimal.valueOf(2.25));

        assertEquals(BigDecimal.valueOf(4.44), result);
    }

    @Test
    void calculateDividendYieldPreferred() {
        PreferredStock preferredStock = new PreferredStock(STOCK_SYMBOL, 0, 5, 2);
        when(mockStockDao.getStock(anyString())).thenReturn(preferredStock);

        BigDecimal result = testCalculatorService.calculateDividendYield(STOCK_SYMBOL, BigDecimal.valueOf(2.25));

        assertEquals(BigDecimal.valueOf(4.44), result);
    }

    @Test
    void calculatePeRatio() {
        CommonStock commonStock = new CommonStock(STOCK_SYMBOL, 10, 100);
        when(mockStockDao.getStock(anyString())).thenReturn(commonStock);

        BigDecimal result = testCalculatorService.calculatePeRatio(STOCK_SYMBOL, BigDecimal.valueOf(5.25));

        assertEquals(BigDecimal.valueOf(0.52), result);
    }

    @Test
    void calculateVolumeWeightedStockPrice() {
        Trade tradeOne = new Trade(ZonedDateTime.now().minusMinutes(14), STOCK_SYMBOL, 10, SellIndicator.SELL, BigDecimal.valueOf(10.25));
        Trade tradeTwo = new Trade(ZonedDateTime.now().minusMinutes(5), STOCK_SYMBOL, 5, SellIndicator.SELL, BigDecimal.valueOf(10.15));
        when(mockTradeDao.getTradesForStockAndTimeFrame(anyString(), any(), any())).thenReturn(Arrays.asList(tradeOne, tradeTwo));

        BigDecimal result = testCalculatorService.calculateVolumeWeightedStockPrice(STOCK_SYMBOL, 15);

        assertEquals(BigDecimal.valueOf(10.22), result);
    }

    @Test
    void calculateAllShareIndex() {
        Trade tradeOne = new Trade(ZonedDateTime.now().minusMinutes(14), STOCK_SYMBOL, 10, SellIndicator.SELL, BigDecimal.valueOf(10.25));
        Trade tradeTwo = new Trade(ZonedDateTime.now().minusMinutes(5), STOCK_SYMBOL, 5, SellIndicator.SELL, BigDecimal.valueOf(10.15));
        when(mockTradeDao.getAllTrades()).thenReturn(Arrays.asList(tradeOne, tradeTwo));

        BigDecimal result = testCalculatorService.calculateAllShareIndex();

        assertEquals(BigDecimal.valueOf(10.20).setScale(2, RoundingMode.HALF_EVEN), result);
    }
}