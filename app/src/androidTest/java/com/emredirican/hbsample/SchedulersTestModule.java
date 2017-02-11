package com.emredirican.hbsample;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Named;

@Module
public class SchedulersTestModule {

  @Named("IO") @Provides public Scheduler provideIOScheduler() {
    return AndroidSchedulers.mainThread();
  }

  @Named("Main") @Provides public Scheduler provideUIScheduler() {
    return AndroidSchedulers.mainThread();
  }
}
