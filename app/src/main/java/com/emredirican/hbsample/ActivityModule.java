package com.emredirican.hbsample;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;

@Module public abstract class ActivityModule<A extends Activity> {

  protected final A activity;

  public ActivityModule(A activity) {
    this.activity = activity;
  }

  @Provides @PerActivity public A provideActivity() {
    return activity;
  }
}
