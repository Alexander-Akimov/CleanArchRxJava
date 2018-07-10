package com.akimov.helloworldrxjava.data.db.storio;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.akimov.helloworldrxjava.presentation.model.StockUpdate;
import com.pushtorefresh.storio3.sqlite.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio3.sqlite.queries.InsertQuery;
import com.pushtorefresh.storio3.sqlite.queries.UpdateQuery;

public class StockUpdatePutResolver extends DefaultPutResolver<StockUpdate> {

  @NonNull
  @Override
  protected InsertQuery mapToInsertQuery(@NonNull StockUpdate object) {
    return InsertQuery.builder()
        .table(StockUpdateTable.TABLE)
        .build();
  }

  @NonNull
  @Override
  protected UpdateQuery mapToUpdateQuery(@NonNull StockUpdate object) {
    return UpdateQuery.builder()
        .table(StockUpdateTable.TABLE)
        .where(StockUpdateTable.Columns.ID + "= ?")
        .whereArgs(object.getId())
        .build();
  }

  @NonNull
  @Override
  protected ContentValues mapToContentValues(@NonNull StockUpdate entity) {
    final ContentValues contentValues = new ContentValues();
    contentValues.put(StockUpdateTable.Columns.ID, entity.getId());
    contentValues.put(StockUpdateTable.Columns.STOCK_SYMBOL, entity.getStockSymbol());
    contentValues.put(StockUpdateTable.Columns.PRICE, entity.getPriceLong());
    contentValues.put(StockUpdateTable.Columns.DATE, entity.getDateLong());

    return contentValues;
  }
}
