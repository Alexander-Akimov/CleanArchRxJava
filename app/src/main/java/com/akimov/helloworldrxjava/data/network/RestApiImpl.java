package com.akimov.helloworldrxjava.data.network;

import com.akimov.helloworldrxjava.data.network.retrofit.RetrofitYahooService;
import com.akimov.helloworldrxjava.data.network.retrofit.RetrofitYahooServiceFactory;
import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lex on 6/9/18.
 */
public class RestApiImpl implements IRestApi {

  @Override
  public Observable<List<StockUpdate>> getQuotes(String symbols) {
    RetrofitYahooService yahooService = new RetrofitYahooServiceFactory().create();

    /*.yqlQuery(symbols)
     .yqlQueryTemp(symbols)
     .yqlQueryLocal()
     */
    return yahooService.yqlQueryLocal()
        .subscribeOn(Schedulers.io())
        .map(r -> r.getQuery().getResults())// transform Observable<YahooStockResult> into Observable<List<YahooStockQuote>>
        .flatMap(list -> Observable.fromIterable(list)
            .map(StockUpdate::create)//convert YahooStockQuote into StockUpdate
            .toList()
        )
        .toObservable();
  }

}
