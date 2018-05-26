package com.akimov.helloworldrxjava.presentation.model;

import com.akimov.helloworldrxjava.data.network.yahoo.json.YahooStockQuote;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockUpdate implements Serializable {
  private Integer id;
  private final String stockSymbol;
  private final BigDecimal price;
  private final Date date;

  private static final NumberFormat PRICE_FORMAT = new DecimalFormat("#0.00");

  public StockUpdate(String stockSymbol, BigDecimal price, Date date) {
    this.stockSymbol = stockSymbol;
    this.price = price;//new BigDecimal(price);
    this.date = date;
  }

  public static StockUpdate create(YahooStockQuote r) {
    return new StockUpdate(r.getSymbol(), r.getRegularMarketPrice(), new Date());

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStockSymbol() {
    return stockSymbol;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Date getDate() {
    return date;
  }

  public String getDateFormat() {
    return new SimpleDateFormat("yyyy-MM-dd hh:mm").format(this.date);
  }

  public String getFormatPrice() {
    return PRICE_FORMAT.format(this.price);
  }
}
