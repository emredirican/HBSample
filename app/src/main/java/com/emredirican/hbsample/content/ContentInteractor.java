package com.emredirican.hbsample.content;

import com.emredirican.hbsample.PerApplication;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

import static com.emredirican.hbsample.util.scheduler.SchedulerModule.IO;

@PerApplication public class ContentInteractor implements Content.Interactor {

  private final ContentService contentService;
  private final Scheduler subscribingScheduler;

  @Inject public ContentInteractor(
      ContentService contentService, @Named(IO) Scheduler subscribingScheduler
  ) {
    this.contentService = contentService;
    this.subscribingScheduler = subscribingScheduler;
  }

  @Override public Observable<ContentViewState> remainingSeconds() {
    return contentService.remainingSeconds()
        .subscribeOn(subscribingScheduler)
        .map(new Function<List<Response>, ContentViewState.Completed>() {
          @Override public ContentViewState.Completed apply(
              List<Response> responses
          ) throws Exception {
            return new ContentViewState.Completed(responses);
          }
        })
        .cast(ContentViewState.class)
        .startWith(new ContentViewState.Loading())
        .onErrorReturn(new Function<Throwable, ContentViewState>() {
          @Override public ContentViewState apply(Throwable throwable) throws Exception {
            return new ContentViewState.Error(throwable);
          }
        });
  }
}
