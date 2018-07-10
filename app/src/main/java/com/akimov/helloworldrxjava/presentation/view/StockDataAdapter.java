package com.akimov.helloworldrxjava.presentation.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akimov.helloworldrxjava.R;
import com.akimov.helloworldrxjava.di.activity.ActivityContext;
import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockDataAdapter extends RecyclerView.Adapter<StockDataAdapter.StockUpdateViewHolder> {

  private final LayoutInflater layoutInflater;
  private final List<StockUpdate> stockList = new ArrayList<>();


  @Inject
  public StockDataAdapter(@ActivityContext @NonNull Context context) {
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

  }

  @NonNull
  @Override
  public StockUpdateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = this.layoutInflater
        .inflate(R.layout.stock_update_item, parent, false);
    StockUpdateViewHolder vh = new StockUpdateViewHolder(v);
    return vh;
  }

  @Override
  public void onBindViewHolder(@NonNull StockUpdateViewHolder holder, int position) {
    StockUpdate stockUpdate = stockList.get(position);

    holder.setStockSymbol(stockUpdate);
  }

  @Override
  public int getItemCount() {
    return stockList.size();
  }

  public void setData(List<StockUpdate> stockUpdateList) {
    stockList.clear();
    stockList.addAll(0, stockUpdateList);
    notifyItemRangeChanged(0, stockUpdateList.size());
    //notifyItemRangeInserted(0, stockUpdateList.size());
    //notifyDataSetChanged();

  }

  public void add(StockUpdate newStockUpdate) {
    //this.stockList.add(stockUpdate);
    //notifyItemInserted(stockList.size() - 1);

    for (StockUpdate stockUpdate : stockList) {
      if (stockUpdate.getStockSymbol().equals
          (newStockUpdate.getStockSymbol())) {
        if (stockUpdate.getPrice().equals
            (newStockUpdate.getPrice())) {
          return;
        }
        break;
      }
    }
    this.stockList.add(0, newStockUpdate);
    notifyItemInserted(0);
  }

  class StockUpdateViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.stock_item_symbol)
    TextView stockSymbol;
    @BindView(R.id.stock_item_date)
    TextView date;
    @BindView(R.id.stock_item_price)
    TextView price;

    StockUpdateViewHolder(View v) {
      super(v);
      ButterKnife.bind(this, v);
    }

    public void setStockSymbol(StockUpdate stockUpdate) {

      this.stockSymbol.setText(stockUpdate.getStockSymbol());
      this.date.setText(stockUpdate.getDateFormat());
      this.price.setText(stockUpdate.getFormatPrice());
    }
  }
}
