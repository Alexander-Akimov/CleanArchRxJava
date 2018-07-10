package com.akimov.helloworldrxjava.data.db.storio;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.akimov.helloworldrxjava.presentation.model.StockUpdate;
import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.get.DefaultGetResolver;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lex on 6/9/18.
 */
public class StockUpdateGetResolver extends DefaultGetResolver<StockUpdate> {

  @NonNull
  @Override
  public StockUpdate mapFromCursor(@NonNull StorIOSQLite storIOSQLite, @NonNull Cursor cursor) {
    final int id = cursor.getInt(cursor.getColumnIndexOrThrow(StockUpdateTable.Columns.ID));
    final long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow(StockUpdateTable.Columns.DATE));
    final long priceLong = cursor.getLong(cursor.getColumnIndexOrThrow(StockUpdateTable.Columns.PRICE));
    final String stockSymbol = cursor.getString(cursor.getColumnIndexOrThrow(StockUpdateTable.Columns.STOCK_SYMBOL));

    final StockUpdate stockUpdate = new StockUpdate(stockSymbol, priceLong, dateLong);
    stockUpdate.setId(id);
    return stockUpdate;
  }

}
