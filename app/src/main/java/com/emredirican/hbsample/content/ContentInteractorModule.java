package com.emredirican.hbsample.content;

import com.emredirican.hbsample.PerApplication;
import dagger.Binds;
import dagger.Module;

@Module public abstract class ContentInteractorModule {

  @Binds @PerApplication
  public abstract Content.Interactor provideInteractor(ContentInteractor interactor);
}
