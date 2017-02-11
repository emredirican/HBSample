package com.emredirican.hbsample.content;

import com.emredirican.hbsample.PerApplication;
import dagger.Binds;
import dagger.Module;

@Module public abstract class ContentPresenterModule {

  @Binds @PerApplication
  public abstract Content.Presenter providePresenter(ContentPresenter contentPresenter);
}
