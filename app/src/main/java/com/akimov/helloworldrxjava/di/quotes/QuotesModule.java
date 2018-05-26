package com.akimov.helloworldrxjava.di.quotes;

import com.akimov.helloworldrxjava.data.repository.QuotesRepository;
import com.akimov.helloworldrxjava.di.activity.PerActivity;
import com.akimov.helloworldrxjava.domain.quotes.interactor.IQuotesInteractor;
import com.akimov.helloworldrxjava.domain.quotes.interactor.QuotesInteractor;
import com.akimov.helloworldrxjava.domain.quotes.repository.IQuotesRepository;
import com.akimov.helloworldrxjava.presentation.presenter.IQuotesPresenter;
import com.akimov.helloworldrxjava.presentation.presenter.QuotesPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public interface QuotesModule {

  @QuotesScope
  @Binds
  IQuotesPresenter provideQuotesPresenter(QuotesPresenter quotesPresenter);

  @QuotesScope
  @Binds
  IQuotesInteractor provideQuotesInteractor(QuotesInteractor quotesInteractor);

  @QuotesScope
  @Binds
  IQuotesRepository provideQuotesRepository(QuotesRepository quotesRepository);

}
