package com.akimov.helloworldrxjava.data.repository;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.akimov.helloworldrxjava.data.db.storio.StorIOFactory;
import com.akimov.helloworldrxjava.data.network.RetrofitYahooServiceFactory;
import com.akimov.helloworldrxjava.data.network.YahooService;

import com.akimov.helloworldrxjava.di.activity.ActivityContext;
import com.akimov.helloworldrxjava.domain.quotes.repository.IQuotesRepository;
import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class QuotesRepository implements IQuotesRepository {

  private final Context context;
  private final String TAG = "QuotesRepository";

  @Inject
  public QuotesRepository(@ActivityContext @NonNull Context context) {
    this.context = context;
  }

  @Override
  public void saveStockUpdateList(List<StockUpdate> stockUpdateList) {
   // stockUpdateList.forEach(stockUpdate -> {});
    for (StockUpdate stockUpdate : stockUpdateList) {
      StorIOFactory.get(this.context)
        .put()
        .object(stockUpdate)
        .prepare()
        .asRxSingle()
        .subscribe();
    }
  }

  @Override
  public Observable<List<StockUpdate>> getQuotes(String symbols) {

    YahooService yahooService = new RetrofitYahooServiceFactory().create();

    return yahooService.yqlQuery(symbols)
        .subscribeOn(Schedulers.io())
        .map(r -> r.getQuery().getResults())// transform Observable<YahooStockResult> into Observable<List<YahooStockQuote>>
        .flatMap(list -> Observable.fromIterable(list)
            .map(StockUpdate::create)//convert YahooStockQuote into StockUpdate
            .toList()
        )
        .toObservable();
  }

  private void log(String title, String message) {
    Log.d(TAG, title + ":" + Thread.currentThread().getName() + ":" + message);
  }
}
