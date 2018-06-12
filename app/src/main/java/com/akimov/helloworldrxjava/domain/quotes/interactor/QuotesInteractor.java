package com.akimov.helloworldrxjava.domain.quotes.interactor;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.Toast;

import com.akimov.helloworldrxjava.data.network.retrofit.RetrofitYahooService;
import com.akimov.helloworldrxjava.data.network.retrofit.RetrofitYahooServiceFactory;
import com.akimov.helloworldrxjava.domain.quotes.repository.IQuotesRepository;
import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class QuotesInteractor implements IQuotesInteractor {

  private final String TAG = "QuotesInteractor";

  private final IQuotesRepository iQuotesRepository;

  private final List<StockUpdate> tempStockUpdateList = new ArrayList<>();

  @Inject
  public QuotesInteractor(IQuotesRepository quotesRepository) {
    this.iQuotesRepository = quotesRepository;
  }

  private boolean contains(StockUpdate stockUpdate) {

    for (StockUpdate tempStockUpdate : tempStockUpdateList) {
      if (stockUpdate.getStockSymbol().equals(tempStockUpdate.getStockSymbol())) {
        if (stockUpdate.getPrice().equals(tempStockUpdate.getPrice())) {
          return true;
        }
        break;
      }
    }
    return false;
  }

  private void addItems(List<StockUpdate> stockUpdateList) {
    tempStockUpdateList.addAll(stockUpdateList);
  }

  @SuppressLint("CheckResult")
  @Override
  public Observable<List<StockUpdate>> getQuotesData(String symbols) {
    /**/
    return Observable.interval(0, 5, TimeUnit.SECONDS)
        .flatMap(i -> iQuotesRepository.getQuotes(symbols))
        .observeOn(AndroidSchedulers.mainThread());


      /*  .flatMap(list -> Observable.fromIterable(list)
            .filter(su -> !QuotesInteractor.this.contains(su))
            .toList()
            .toObservable()
        )
        .doOnNext(list -> {
          QuotesInteractor.this.addItems(list);
          // iQuotesRepository.saveStockUpdateList(list);
        })*/
        /*return  iQuotesRepository.getQuotes(symbols)
        .observeOn(AndroidSchedulers.mainThread());
        */
      /*    .doOnNext(list -> {
          stockUpdateList.clear();
          // stockUpdateList

        })*/

    //yahooService.yqlQuery(symbols) //returns Single<YahooStockResult>
    // do in the background thread
//                .doOnNext(e -> Log.d(TAG, "on-next:" +
//                        Thread.currentThread().getName() + ":" + e))
    //      .toObservable()


    // .doOnNext(this::saveStockUpdate)
         /*  Observable.just(
                new StockUpdate("APPL", 645.1, new Date()),
                new StockUpdate("GOOGLE", 12.43, new Date()),
                new StockUpdate("TWTR", 1.43, new Date()))
                //.subscribe(stockSymbol -> stockDataAdapter.add(stockSymbol));
                .doOnNext(s -> Log.d(TAG, "on next " + s))
                .subscribe(stockDataAdapter::add);


    */
  }

  private void saveStockUpdate(StockUpdate stockUpdate) {
//    stockUpdateList.clear();
//    stockUpdateList.addAll();
  }

  private void log(String title, String message) {
    Log.d(TAG, title + ":" + Thread.currentThread().getName() + ":" + message);
  }

  private void log(Throwable throwable) {
    Log.e(TAG, "Error", throwable);
  }

}
