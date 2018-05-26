package com.akimov.helloworldrxjava.presentation.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.akimov.helloworldrxjava.QuotesApplication;
import com.akimov.helloworldrxjava.di.activity.ActivityModule;
import com.akimov.helloworldrxjava.di.application.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //this.getApplicationComponent().inject(this);
  }

  protected AppComponent getApplicationComponent() {
    return QuotesApplication.get(this).getApplicationComponent();
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}
