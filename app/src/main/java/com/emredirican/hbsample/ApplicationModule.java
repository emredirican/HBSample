package com.emredirican.hbsample;

import dagger.Module;
import dagger.Provides;

@Module public class ApplicationModule {
  private final SampleApplication application;

  public ApplicationModule(SampleApplication application) {
    this.application = application;
  }

  @PerApplication @Provides public SampleApplication provideAppContext() {
    return application;
  }
}
