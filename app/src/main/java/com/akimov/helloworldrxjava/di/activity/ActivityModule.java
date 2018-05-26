package com.akimov.helloworldrxjava.di.activity;

import android.app.Activity;
import android.content.Context;

import com.akimov.helloworldrxjava.di.quotes.QuotesScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
  private final Activity _context;

  public ActivityModule(Activity activity) {
    this._context = activity;
  }

  @ActivityContext
  @Provides
  @QuotesScope
  Context context() {
    return this._context;
  }

}
