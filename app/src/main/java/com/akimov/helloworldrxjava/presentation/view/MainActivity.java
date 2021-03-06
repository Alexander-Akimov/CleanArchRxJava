package com.akimov.helloworldrxjava.presentation.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akimov.helloworldrxjava.QuotesApplication;
import com.akimov.helloworldrxjava.R;
import com.akimov.helloworldrxjava.di.quotes.DaggerQuotesComponent;
import com.akimov.helloworldrxjava.di.quotes.QuotesComponent;
import com.akimov.helloworldrxjava.presentation.model.StockUpdate;
import com.akimov.helloworldrxjava.presentation.presenter.IQuotesPresenter;
import com.akimov.helloworldrxjava.presentation.presenter.QuotesPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity implements IQuotesListView {

  @Inject
  public IQuotesPresenter iQuotesListPresenter;

  @Inject
  public StockDataAdapter stockDataAdapter;

  @BindView(R.id.quotes_layout)
  ConstraintLayout constraintLayout;

  @BindView(R.id.helloWorldTxt)
  TextView helloText;

  @BindView(R.id.stockUpdatesRecyclerView)
  RecyclerView recyclerView;

  @BindView(R.id.progressBar)
  ProgressBar progressBar;

  private QuotesComponent quotesComponent;
  private Unbinder unbinder;


  @SuppressLint("CheckResult")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);

    unbinder = ButterKnife.bind(this);

    initInjector();
    quotesComponent.inject(this);

    setupRecyclerView();
    progressBar.setVisibility(View.INVISIBLE);

    iQuotesListPresenter.bindView(this);
    String symbols = "YHOO,AAPL,GOOG,MSFT,FTSE,N225,BTC-USD,JPY=X,GBPUSD=X,VIX,TNX,EURUSD=X,SI=F,GC=F,CL=F,RUT,GSPC,DJI";
    //String symbols = ",AAPL,GOOG,MSFT";
    iQuotesListPresenter.loadQuotesList(symbols);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
    this.iQuotesListPresenter.unbindView();
    //Log.d("MainActivity.Destroy","onDestroy");
  }


  private void setupRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(stockDataAdapter);
    recyclerView.setHasFixedSize(false);
  }

  @Override
  public void renderQuotesList(List<StockUpdate> stockUpdateList) {

    stockDataAdapter.setData(stockUpdateList);

  }

  private void initInjector() {
    quotesComponent = DaggerQuotesComponent.builder()
        .appComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }


  @Override
  public void setHeader(String header) {
    helloText.setText(header);
  }

  @Override
  public void showError(String message) {
    Snackbar snackbar = Snackbar.make(constraintLayout, message, Snackbar.LENGTH_LONG);
    snackbar.show();
  }

  @Override
  public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgress() {
    progressBar.setVisibility(View.INVISIBLE);
  }

  @Override
  public void renderQuote(StockUpdate stockUpdate) {
    stockDataAdapter.add(stockUpdate);
    recyclerView.smoothScrollToPosition(0);
  }
}

