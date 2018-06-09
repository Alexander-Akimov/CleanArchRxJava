package com.akimov.helloworldrxjava.data.db;

import android.content.Context;

import com.akimov.helloworldrxjava.data.db.storio.StorIOFactory;
import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import java.util.List;

/**
 * Created by lex on 6/9/18.
 */
public class DbApiImpl implements IDBApi {

  private final Context context;

  public DbApiImpl(Context context)
  {
    this.context = context;
  }
  @Override
  public void insertData(List<StockUpdate> stockUpdateList) {
    StorIOFactory.get(this.context)
        .put()
        .object(stockUpdateList)
        .prepare()
        .asRxSingle()//executeAsBlocking()
        .subscribe();
  }
}
