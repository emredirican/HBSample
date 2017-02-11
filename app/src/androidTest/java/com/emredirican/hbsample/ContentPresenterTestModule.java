package com.emredirican.hbsample;

import com.emredirican.hbsample.content.Content;
import com.emredirican.hbsample.content.ContentPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import javax.inject.Named;

@Module public class ContentPresenterTestModule {

  @Provides @PerApplication public Content.Presenter providePresenter(
      Content.Interactor interactor, @Named("Main") Scheduler scheduler
  ) {
    return new ContentPresenter(interactor, scheduler);
  }
}
