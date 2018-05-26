package com.akimov.helloworldrxjava.domain.quotes.interactor;

import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import java.util.List;

import io.reactivex.Observable;

public interface IQuotesInteractor {
    Observable<List<StockUpdate>> getQuotesData(String symbols);
}
