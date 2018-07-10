package com.akimov.helloworldrxjava.data.db;

import android.content.Context;

import com.akimov.helloworldrxjava.data.db.storio.StorIOFactory;
import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by lex on 6/9/18.
 */
public class DataBaseSourceImpl implements IDataBaseSource {

  private final Context context;

  public DataBaseSourceImpl(Context context) {
    this.context = context;
  }

  @Override
  public Single insertData(List<StockUpdate> stockUpdateList) {
    return StorIOFactory.get(this.context)
        .put()
        .objects(stockUpdateList)
        .prepare()
        .asRxSingle();//executeAsBlocking()
  }
}
