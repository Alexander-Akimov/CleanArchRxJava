package com.akimov.helloworldrxjava.data.db;

import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import java.util.List;

/**
 * Created by lex on 6/9/18.
 */
public interface IDBApi {
  void insertData(List<StockUpdate> stockUpdateList);
}
