package com.akimov.helloworldrxjava.presentation.view;

import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

public interface IQuotesListView {

  void renderQuotesList();

  void renderQuote(StockUpdate stockUpdate);

  void setHeader(String header);

  void showError();
}
