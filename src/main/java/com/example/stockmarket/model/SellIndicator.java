package com.example.stockmarket.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum SellIndicator {
    BUY, SELL;

    private static final List<SellIndicator> VALUES =
            (Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static SellIndicator randomSellIndicator()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
