package com.akimov.helloworldrxjava.domain;

import android.annotation.SuppressLint;
import android.util.Log;

import com.akimov.helloworldrxjava.data.RetrofitYahooServiceFactory;
import com.akimov.helloworldrxjava.data.YahooService;
import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class QuotesInteractor implements IQuotesInteractor {

  private final String TAG = "QuotesInteractor";

  public QuotesInteractor() {

  }

  @SuppressLint("CheckResult")
  @Override
  public Observable<StockUpdate> getQuotesData(String symbols) {
         /*  Observable.just(
                new StockUpdate("APPL", 645.1, new Date()),
                new StockUpdate("GOOGLE", 12.43, new Date()),
                new StockUpdate("TWTR", 1.43, new Date()))
                //.subscribe(stockSymbol -> stockDataAdapter.add(stockSymbol));
                .doOnNext(s -> Log.d(TAG, "on next " + s))
                .subscribe(stockDataAdapter::add);


        Observable.just("First item", "Second item")
                .subscribeOn(Schedulers.io())
                .doOnNext(e -> Log.d(TAG, "on-next:" + Thread.currentThread().getName() + ":" + e))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(e -> Log.d(TAG, "subscribe:" + Thread.currentThread().getName() + ":" + e));*/


      /*  PublishSubject<Integer> observable = PublishSubject.create();

//       observable
//                .observeOn(Schedulers.computation())
//                .subscribe(v -> Log.d(TAG, "subscribe:" + v.toString()), this::log);
//

       observable.toFlowable(BackpressureStrategy.MISSING)
               //.onBackpressureDrop()
               //.sample(10, TimeUnit.MILLISECONDS)
               .onBackpressureBuffer()
               .buffer(50)
               .observeOn(Schedulers.computation())
               .subscribe(v -> Log.d(TAG, "subscribe:" + v.toString()), this::log);

        for (int i = 0; i < 1000000; i++) {
            observable.onNext(i);
        }*/

    YahooService yahooService = new RetrofitYahooServiceFactory().create();


    return Observable.interval(0, 5, TimeUnit.SECONDS)
        .flatMap(i -> yahooService.yqlQuery(symbols)
            .toObservable())
        //yahooService.yqlQuery(symbols) //returns Single<YahooStockResult>
        .subscribeOn(Schedulers.io()) // do in the background thread
//                .doOnNext(e -> Log.d(TAG, "on-next:" +
//                        Thread.currentThread().getName() + ":" + e))
        //      .toObservable()
        .map(r -> r.getQuery().getResults()) // transform Observable<YahooStockResult> into Observable<List<YahooStockQuote>>
        .flatMap(Observable::fromIterable)
        .map(StockUpdate::create) //convert YahooStockQuote into StockUpdate
        .observeOn(AndroidSchedulers.mainThread());

  }

  private void log(Throwable throwable) {
    Log.e(TAG, "Error", throwable);

  }
}
