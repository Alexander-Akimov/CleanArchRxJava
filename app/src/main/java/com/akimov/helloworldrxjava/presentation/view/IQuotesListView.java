package com.akimov.helloworldrxjava.presentation.view;

import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import java.util.List;

public interface IQuotesListView {

  void renderQuotesList(List<StockUpdate> stockUpdateList);

  void renderQuote(StockUpdate stockUpdate);

  void setHeader(String header);

  void showError();
}
