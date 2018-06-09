package com.akimov.helloworldrxjava.data.network;

import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lex on 6/9/18.
 */
public interface IRestApi {

  Observable<List<StockUpdate>> getQuotes(String symbols);
}
