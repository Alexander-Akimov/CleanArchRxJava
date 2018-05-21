package com.akimov.helloworldrxjava.presentation.view;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;


import com.akimov.helloworldrxjava.R;
import com.akimov.helloworldrxjava.data.RetrofitYahooServiceFactory;
import com.akimov.helloworldrxjava.data.YahooService;
import com.akimov.helloworldrxjava.presentation.model.StockUpdate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.helloWorldTxt)
    TextView helloText;

    @BindView(R.id.stockUpdatesRecyclerView)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private StockDataAdapter stockDataAdapter;

    private final String TAG = "APP";

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ButterKnife.bind(this);

        Observable.just("Hello! Please use this app responsibly!")
                .subscribe(s -> helloText.setText(s));

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        stockDataAdapter = new StockDataAdapter();
        recyclerView.setAdapter(stockDataAdapter);
        recyclerView.setHasFixedSize(true);



      /*  Observable.just(
                new StockUpdate("APPL", 645.1, new Date()),
                new StockUpdate("GOOGLE", 12.43, new Date()),
                new StockUpdate("TWTR", 1.43, new Date()))
                //.subscribe(stockSymbol -> stockDataAdapter.add(stockSymbol));
                .doOnNext(s -> Log.d(TAG, "on next " + s))
                .subscribe(stockDataAdapter::add);


        Observable.just("First item", "Second item")
                .subscribeOn(Schedulers.io())
                .doOnNext(e -> Log.d(TAG, "on-next:" + Thread.currentThread().getName() + ":" + e))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(e -> Log.d(TAG, "subscribe:" + Thread.currentThread().getName() + ":" + e));*/


      /*  PublishSubject<Integer> observable = PublishSubject.create();

//       observable
//                .observeOn(Schedulers.computation())
//                .subscribe(v -> Log.d(TAG, "subscribe:" + v.toString()), this::log);
//

       observable.toFlowable(BackpressureStrategy.MISSING)
               //.onBackpressureDrop()
               //.sample(10, TimeUnit.MILLISECONDS)
               .onBackpressureBuffer()
               .buffer(50)
               .observeOn(Schedulers.computation())
               .subscribe(v -> Log.d(TAG, "subscribe:" + v.toString()), this::log);

        for (int i = 0; i < 1000000; i++) {
            observable.onNext(i);
        }*/

        YahooService yahooService = new RetrofitYahooServiceFactory().create();

        String symbols = "YHOO,AAPL,GOOG,MSFT";

        Observable.interval(0, 5, TimeUnit.SECONDS)
                .flatMap(i -> yahooService.yqlQuery(symbols)
                        .toObservable())
                //yahooService.yqlQuery(symbols) //returns Single<YahooStockResult>
                .subscribeOn(Schedulers.io()) // do in the background thread
//                .doOnNext(e -> Log.d(TAG, "on-next:" +
//                        Thread.currentThread().getName() + ":" + e))
                //      .toObservable()
                .map(r -> r.getQuery().getResults()) // transform Observable<YahooStockResult> into Observable<List<YahooStockQuote>>
                .flatMap(Observable::fromIterable)
                .map(StockUpdate::create) //convert YahooStockQuote into StockUpdate
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stockUpdate -> {
                    Log.d(TAG, "New update " + stockUpdate.getStockSymbol());
                    stockDataAdapter.add(stockUpdate);
                });
    }

    private void log(Throwable throwable) {
        Log.e(TAG, "Error", throwable);

    }
}

