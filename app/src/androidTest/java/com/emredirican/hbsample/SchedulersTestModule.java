package com.emredirican.hbsample;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Named;

import static com.emredirican.hbsample.util.scheduler.SchedulerModule.IO;
import static com.emredirican.hbsample.util.scheduler.SchedulerModule.MAIN;

@Module
public class SchedulersTestModule {

  @Named(IO) @Provides public Scheduler provideIOScheduler() {
    return AndroidSchedulers.mainThread();
  }

  @Named(MAIN) @Provides public Scheduler provideUIScheduler() {
    return AndroidSchedulers.mainThread();
  }
}
