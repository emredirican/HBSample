package com.emredirican.hbsample.content;

import io.reactivex.Observable;

public interface Content {

  interface View {

    void render(ContentViewState viewState);
  }

  interface Presenter {

    void update();

    void attachView(View view);

    void detachView();
  }

  interface Interactor {

    Observable<ContentViewState> remainingSeconds();
  }
}
