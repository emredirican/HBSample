package com.emredirican.hbsample.content;

import com.emredirican.hbsample.PerApplication;
import io.reactivex.Scheduler;
import io.reactivex.observers.DefaultObserver;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import javax.inject.Named;
import timber.log.Timber;

@PerApplication public class ContentPresenter implements Content.Presenter {

  private final Content.Interactor interactor;
  private final Scheduler observingScheduler;
  private WeakReference<Content.View> view;

  @Inject public ContentPresenter(
      Content.Interactor interactor, @Named("Main") Scheduler observingScheduler
  ) {
    this.interactor = interactor;
    this.observingScheduler = observingScheduler;
  }

  @Override public void update() {
    DefaultObserver<ContentViewState> observer = new DefaultObserver<ContentViewState>() {
      @Override public void onNext(ContentViewState value) {
        if (view != null) {
          view.get().render(value);
        }
      }

      @Override public void onError(Throwable e) {
        Timber.e(e.getMessage());
        //TODO
      }

      @Override public void onComplete() {
        //nothing to do for now
      }
    };
    interactor.remainingSeconds().observeOn(observingScheduler).subscribe(observer);
  }

  @Override public void attachView(Content.View view) {
    this.view = new WeakReference<>(view);
  }

  @Override public void detachView() {
    this.view = null;
  }
}
