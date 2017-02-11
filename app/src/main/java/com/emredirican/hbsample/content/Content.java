package com.emredirican.hbsample.content;

import io.reactivex.Observable;

public interface Content {

  public interface View {

    void render(ContentViewState viewState);
  }

  public interface Presenter {

    void update();

    void attachView(View view);

    void detachView();
  }

  public interface Interactor {

    Observable<ContentViewState> remainingSeconds();
  }
}
