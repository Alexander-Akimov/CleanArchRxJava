package com.akimov.helloworldrxjava.di.quotes;

import com.akimov.helloworldrxjava.di.activity.ActivityComponent;
import com.akimov.helloworldrxjava.di.activity.ActivityModule;
import com.akimov.helloworldrxjava.di.activity.PerActivity;
import com.akimov.helloworldrxjava.di.application.AppComponent;
import com.akimov.helloworldrxjava.presentation.view.MainActivity;

import dagger.Component;

@QuotesScope
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, QuotesModule.class})
public interface QuotesComponent extends ActivityComponent {
  void inject(MainActivity mainActivity);
}
