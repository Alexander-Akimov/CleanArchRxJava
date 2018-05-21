package com.akimov.helloworldrxjava.domain;

import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import io.reactivex.Observable;

public interface IQuotesInteractor {
    Observable<StockUpdate> getQuotesData(String symbols);
}
