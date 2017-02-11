package com.emredirican.hbsample.util.scheduler;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Named;

@Module public class SchedulerModule {

  @Named("IO") @Provides public Scheduler provideIOScheduler() {
    return Schedulers.io();
  }

  @Named("Main") @Provides public Scheduler provideUIScheduler() {
    return AndroidSchedulers.mainThread();
  }
}
