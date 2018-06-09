package com.akimov.helloworldrxjava.data.network.retrofit;

import com.akimov.helloworldrxjava.data.models.yahoojson.YahooStockResult;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitYahooService {

//    @GET("yql?format=json")
//    Single<YahooStockResult> yqlQuery(
//            @Query("q") String query,
//            @Query("env") String env
//    );
    @GET("quote")
    Single<YahooStockResult> yqlQuery(
            @Query("symbols") String symbols
    );
}
