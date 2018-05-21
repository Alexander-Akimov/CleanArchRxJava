package com.akimov.helloworldrxjava.presentation.presenter;

import com.akimov.helloworldrxjava.presentation.view.IQuotesListView;

public interface IQuotesPresenter {

  void bindView(IQuotesListView iQuotesListView);

  void unbindView();

  void loadQuotesList(String symbols);

}
