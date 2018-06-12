package com.akimov.helloworldrxjava;

import android.app.Activity;
import android.app.Application;

import com.akimov.helloworldrxjava.di.application.AppModule;
import com.akimov.helloworldrxjava.di.application.AppComponent;
import com.akimov.helloworldrxjava.di.application.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

public class QuotesApplication extends Application {

  private AppComponent applicationComponent;

  public AppComponent getApplicationComponent() {
    return applicationComponent;
  }

  public static QuotesApplication get(Activity activity) {
    return (QuotesApplication) activity.getApplication();
  }


  @Override
  public void onCreate() {
    super.onCreate();
   /* if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);*/
    applicationComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();

  }
}
