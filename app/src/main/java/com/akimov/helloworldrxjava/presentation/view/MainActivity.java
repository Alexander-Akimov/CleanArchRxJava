package com.akimov.helloworldrxjava.presentation.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.akimov.helloworldrxjava.R;
import com.akimov.helloworldrxjava.presentation.model.StockUpdate;
import com.akimov.helloworldrxjava.presentation.presenter.IQuotesPresenter;
import com.akimov.helloworldrxjava.presentation.presenter.QuotesPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements IQuotesListView {


  IQuotesPresenter iQuotesListPresenter; //TODO: to be injected
  StockDataAdapter stockDataAdapter; //TODO: to be injected

  @BindView(R.id.quotes_layout)
  ConstraintLayout constraintLayout;

  @BindView(R.id.helloWorldTxt)
  TextView helloText;

  @BindView(R.id.stockUpdatesRecyclerView)
  RecyclerView recyclerView;
  private Unbinder unbinder;

  @SuppressLint("CheckResult")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);

    unbinder = ButterKnife.bind(this);

    setupRecyclerView();

    iQuotesListPresenter = new QuotesPresenter();
    iQuotesListPresenter.bindView(this);
    String symbols = "YHOO,AAPL,GOOG,MSFT";
    iQuotesListPresenter.loadQuotesList(symbols);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
    this.iQuotesListPresenter.unbindView();
  }

  private void setupRecyclerView() {
    stockDataAdapter = new StockDataAdapter();
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(stockDataAdapter);
    recyclerView.setHasFixedSize(true);
  }

  @Override
  public void renderQuotesList() {

  }

  @Override
  public void setHeader(String header) {
    helloText.setText(header);
  }

  @Override
  public void showError() {
    Snackbar snackbar = Snackbar.make(constraintLayout, getString(R.string.common_error), Snackbar.LENGTH_LONG);
    snackbar.show();
  }

  @Override
  public void renderQuote(StockUpdate stockUpdate) {
    stockDataAdapter.add(stockUpdate);
  }
}

