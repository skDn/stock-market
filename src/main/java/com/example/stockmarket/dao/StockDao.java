package com.example.stockmarket.dao;

import com.example.stockmarket.model.Stock;

public interface StockDao {

    Stock getStock(String symbol);

    void checkIfStockValid(String stock);

    String[] getAllStockKeys();
}
