package com.akimov.helloworldrxjava.di.activity;


import android.app.Activity;
import android.content.Context;

import com.akimov.helloworldrxjava.di.application.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  Context context();
}
