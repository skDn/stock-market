package com.example.stockmarket.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Trade {

    @NotNull(message = "timestamp cannot be null")
    private ZonedDateTime timestamp;

    @NotNull(message = "stock cannot be null")
    private String stock;

    @Min(value = 1, message = "quantity of shares cannot be less than 1")
    @NotNull(message = "quantity of shares cannot be null")
    private Integer quantityOfShares;

    @NotNull(message = "sell indicator cannot be null")
    private SellIndicator sellIndicator;

    @Min(value = 1, message = "price cannot be less than 1")
    @NotNull(message = "price cannot be null")
    private BigDecimal price;

    public Trade() {
    }

    public Trade(@NotNull(message = "timestamp cannot be null") ZonedDateTime timestamp, @Min(value = 1, message = "stock cannot be less than 1") @NotNull(message = "stock cannot be null") String stock, @Min(value = 1, message = "quantity of shares cannot be less than 1") @NotNull(message = "quantity of shares cannot be null") Integer quantityOfShares, @NotNull(message = "sell indicator cannot be null") SellIndicator sellIndicator, @Min(value = 1, message = "price cannot be less than 1") @NotNull(message = "price cannot be null") BigDecimal price) {
        this.timestamp = timestamp;
        this.stock = stock;
        this.quantityOfShares = quantityOfShares;
        this.sellIndicator = sellIndicator;
        this.price = price;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Integer getQuantityOfShares() {
        return quantityOfShares;
    }

    public void setQuantityOfShares(Integer quantityOfShares) {
        this.quantityOfShares = quantityOfShares;
    }

    public SellIndicator getSellIndicator() {
        return sellIndicator;
    }

    public void setSellIndicator(SellIndicator sellIndicator) {
        this.sellIndicator = sellIndicator;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
