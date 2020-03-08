package com.example.stockmarket.dao;

import com.example.stockmarket.model.CommonStock;
import com.example.stockmarket.model.PreferredStock;
import com.example.stockmarket.model.Stock;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class InMemoryStockDao implements StockDao {

    private Map<String, Stock> db;

    public InMemoryStockDao() {
        db = new HashMap<>();
        db.put("TEA", new CommonStock("TEA", 0, 100));
        db.put("POP", new CommonStock("POP", 8, 100));
        db.put("ALE", new CommonStock("ALE", 23, 60));
        db.put("GIN", new PreferredStock("GIN", 8, 2, 100));
        db.put("JOE", new CommonStock("JOE", 13, 250));
    }

    @Override
    public Stock getStock(String symbol) {
        if (!db.containsKey(symbol)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested stock is not found");
        }
        return db.get(symbol);
    }

    @Override
    public void checkIfStockValid(String stock) {
        if (!db.containsKey(stock)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided stock is not valud");
        }
    }

    @Override
    public String[] getAllStockKeys() {
        Set<String> keySet = db.keySet();
        String[] keys = new String[keySet.size()];
        return keySet.toArray(keys);
    }
}
