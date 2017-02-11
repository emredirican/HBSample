package com.emredirican.hbsample;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;
import timber.log.Timber;

public class SampleApplication extends Application {

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    applicationComponent = buildComponent();
    applicationComponent.injectMembers(this);

    if (LeakCanary.isInAnalyzerProcess(this)) {
      return;
    }
    LeakCanary.install(this);

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  protected ApplicationComponent buildComponent() {
    return SampleApplicationComponent.Initializer.init(this);
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
