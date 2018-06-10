package com.akimov.helloworldrxjava.data.repository;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.akimov.helloworldrxjava.data.db.DbApiImpl;
import com.akimov.helloworldrxjava.data.db.IDBApi;
import com.akimov.helloworldrxjava.data.db.storio.StorIOFactory;
import com.akimov.helloworldrxjava.data.network.IRestApi;
import com.akimov.helloworldrxjava.data.network.RestApiImpl;

import com.akimov.helloworldrxjava.di.activity.ActivityContext;
import com.akimov.helloworldrxjava.domain.quotes.repository.IQuotesRepository;
import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class QuotesRepository implements IQuotesRepository {

  private final String TAG = "QuotesRepository";
  private Context context;
  private IRestApi restApi;
  private IDBApi dbApi;


  @Inject
  public QuotesRepository(@ActivityContext @NonNull Context context) {
    this.restApi = new RestApiImpl();
    this.dbApi = new DbApiImpl(context);
    this.context = context;
  }

  @Override
  public void saveStockUpdateList(List<StockUpdate> stockUpdateList) {
    this.dbApi.insertData(stockUpdateList);

  }

  @Override
  public Observable<List<StockUpdate>> getQuotes(String symbols) {
    return restApi.getQuotes(symbols);
  }

}
