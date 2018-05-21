package com.akimov.helloworldrxjava.data.yahoo.json;


import java.util.List;

public class YahooStockQueryResponse {

    private List<YahooStockQuote> result;

    public List<YahooStockQuote> getResults() {
        return result;
    }
}
