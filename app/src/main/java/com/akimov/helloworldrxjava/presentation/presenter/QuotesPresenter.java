package com.akimov.helloworldrxjava.presentation.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.akimov.helloworldrxjava.domain.IQuotesInteractor;
import com.akimov.helloworldrxjava.domain.QuotesInteractor;
import com.akimov.helloworldrxjava.presentation.model.StockUpdate;
import com.akimov.helloworldrxjava.presentation.view.IQuotesListView;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class QuotesPresenter implements IQuotesPresenter {

  IQuotesInteractor iQuotesListInteractor; //TODO: to be injected

  private IQuotesListView iQuotesListView;

  private final String TAG = "QuotesPresenter";

  private final CompositeDisposable disposables;

  public QuotesPresenter() {
    disposables = new CompositeDisposable();
    iQuotesListInteractor = new QuotesInteractor();
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


  @SuppressLint("CheckResult")
  @Override
  public void loadQuotesList(String symbols) {
    Observable.just("Hello! Please use this app responsibly!")
        .subscribe(s -> iQuotesListView.setHeader(s));

    Disposable getQuotesSubscription = iQuotesListInteractor.getQuotesData(symbols)
        .subscribe(this::handleSuccessLoadQuotes, this::handleErrorLoadQuotes);
    addDisposable(getQuotesSubscription);
  }

  private void handleErrorLoadQuotes(Throwable throwable) {
    iQuotesListView.showError();
  }

  private void handleSuccessLoadQuotes(StockUpdate stockUpdate) {
    Log.d(TAG, "New update " + stockUpdate.getStockSymbol());
    iQuotesListView.renderQuote(stockUpdate);
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
