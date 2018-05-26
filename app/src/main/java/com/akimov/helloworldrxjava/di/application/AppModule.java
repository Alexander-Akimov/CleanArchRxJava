package com.akimov.helloworldrxjava.di.application;

import android.content.Context;

import com.akimov.helloworldrxjava.QuotesApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
  private final Context appContext;

  public AppModule(Context application) {
    this.appContext = application;
  }
  @Provides
  @Singleton
  Context appContext() {
    return this.appContext;
  }

}
