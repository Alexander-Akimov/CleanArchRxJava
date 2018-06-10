package com.akimov.helloworldrxjava.presentation.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.akimov.helloworldrxjava.domain.quotes.interactor.IQuotesInteractor;
import com.akimov.helloworldrxjava.domain.quotes.interactor.QuotesInteractor;
import com.akimov.helloworldrxjava.presentation.model.StockUpdate;
import com.akimov.helloworldrxjava.presentation.view.IQuotesListView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QuotesPresenter implements IQuotesPresenter {

  private final IQuotesInteractor quotesInteractor; //TODO: to be injected

  private IQuotesListView iQuotesListView;

  private static final String TAG = "QuotesPresenter";

  private final CompositeDisposable disposables;

  @Inject
  public QuotesPresenter(IQuotesInteractor quotesInteractor) {
    this.disposables = new CompositeDisposable();
    this.quotesInteractor = quotesInteractor;
  }

  @Override
  public void bindView(IQuotesListView iQuotesListView) {
    this.iQuotesListView = iQuotesListView;

  }

  @Override
  public void unbindView() {
    //TODO: dispose all dependencies
    this.dispose();
    iQuotesListView = null;
  }

 /* public static int importantLongTask(Integer i) {
    try {
      long minMillis = 10L;
      long maxMillis = 1000L;
      log("Working on ", i.toString());
      final long waitingTime = (long) (minMillis + (Math.random() *
          maxMillis - minMillis));
      Thread.sleep(waitingTime);
      return i;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

   static void log(String title, String message) {
    Log.d(TAG, title + ":" + Thread.currentThread().getName() + ":" + message);
  }*/

  @SuppressLint("CheckResult")
  @Override
  public void loadQuotesList(String symbols) {
    Observable.just("Hello! Please use this app responsibly!")
        .subscribe(s -> iQuotesListView.setHeader(s));

   /* Observable.range(1, 100)
        .flatMap(i -> Observable.just(i)
            .subscribeOn(Schedulers.io())
            .map(QuotesPresenter::importantLongTask)
        )
        .map(Object::toString)
        .subscribe(e -> log("subscribe", e));
*/

    Disposable getQuotesSubscription = quotesInteractor.getQuotesData(symbols)
        .subscribe(this::handleSuccessLoadQuotes, this::handleErrorLoadQuotes);
    addDisposable(getQuotesSubscription);
  }

  private void handleErrorLoadQuotes(Throwable throwable) {

    iQuotesListView.showError(throwable.getMessage());
  }

  private void handleSuccessLoadQuotes(List<StockUpdate> stockUpdateList) {
   // Log.d(TAG, "New update " + stockUpdate.getStockSymbol());
    //iQuotesListView.renderQuote(stockUpdate);

    iQuotesListView.renderQuotesList(stockUpdateList);

  }

  private void addDisposable(Disposable disposable) {

    disposables.add(disposable);
  }

  private void dispose() {
    if (!disposables.isDisposed()) {
      disposables.dispose();
    }
  }
}
