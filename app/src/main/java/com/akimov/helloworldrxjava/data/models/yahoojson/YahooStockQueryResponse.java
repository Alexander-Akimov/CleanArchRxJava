package com.akimov.helloworldrxjava.data.models.yahoojson;


import java.util.List;

public class YahooStockQueryResponse {

    private List<YahooStockQuote> result;

    public List<YahooStockQuote> getResults() {
        return result;
    }
}
