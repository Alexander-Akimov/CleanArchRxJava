package com.akimov.helloworldrxjava.data.yahoo.json;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class YahooStockQuote {

    private String symbol;

    private String shortName;

    private BigDecimal regularMarketPrice;

    private BigDecimal regularMarketDayLow;

    private BigDecimal regularMarketDayHigh;

    private BigDecimal regularMarketVolume;



    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public BigDecimal getRegularMarketPrice() {
        return regularMarketPrice;
    }

    public BigDecimal getRegularMarketDayLow() {
        return regularMarketDayLow;
    }

    public BigDecimal getRegularMarketDayHigh() {
        return regularMarketDayHigh;
    }

    public BigDecimal getRegularMarketVolume() {
        return regularMarketVolume;
    }
}
