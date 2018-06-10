package com.akimov.helloworldrxjava.data.network.retrofit;

import com.akimov.helloworldrxjava.data.network.ApiUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitYahooServiceFactory {

  HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
      .setLevel(HttpLoggingInterceptor.Level.BODY);

  OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

  Retrofit retrofit = new Retrofit.Builder()
      .client(client)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(ApiUtils.LOCAL_BASE_URL)//LOCAL_BASE_URL
      .build();

  public RetrofitYahooService create() {
    return retrofit.create(RetrofitYahooService.class);
  }

}
