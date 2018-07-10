package com.akimov.helloworldrxjava.data.db;

import com.akimov.helloworldrxjava.presentation.model.StockUpdate;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResults;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by lex on 6/9/18.
 */
public interface IDataBaseSource {
  Single insertData(List<StockUpdate> stockUpdateList);
}
