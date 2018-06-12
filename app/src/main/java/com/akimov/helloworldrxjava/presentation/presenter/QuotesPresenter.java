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

  private final IQuotesInteractor quotesInteractor;

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


  @SuppressLint("CheckResult")
  @Override
  public void loadQuotesList(String symbols) {
    iQuotesListView.showProgress();
    Observable.just("Hello! Please use this app responsibly!")
        .subscribe(s -> iQuotesListView.setHeader(s));


    Disposable getQuotesSubscription = quotesInteractor.getQuotesData(symbols)
        .subscribe(this::handleSuccessLoadQuotes, this::handleErrorLoadQuotes);
    addDisposable(getQuotesSubscription);
  }

  private void handleErrorLoadQuotes(Throwable throwable) {
    iQuotesListView.hideProgress();
    iQuotesListView.showError(throwable.getMessage());
  }

  private void handleSuccessLoadQuotes(List<StockUpdate> stockUpdateList) {

    // Log.d(TAG, "New update " + stockUpdate.getStockSymbol());
    //iQuotesListView.renderQuote(stockUpdate);

    iQuotesListView.renderQuotesList(stockUpdateList);
    iQuotesListView.hideProgress();

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
