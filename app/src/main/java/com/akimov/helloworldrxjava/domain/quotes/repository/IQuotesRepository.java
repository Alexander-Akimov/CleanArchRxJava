package com.akimov.helloworldrxjava.domain.quotes.repository;

import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import java.util.List;

import io.reactivex.Observable;


public interface IQuotesRepository {

  void saveStockUpdateList(List<StockUpdate> stockUpdateList);

  Observable<List<StockUpdate>> getQuotes(String symbols);
}
