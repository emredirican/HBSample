package com.emredirican.hbsample.util.scheduler;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Named;

@Module public class SchedulerModule {

  public static final String IO = "IO";
  public static final String MAIN = "Main";

  @Named(IO) @Provides public Scheduler provideIOScheduler() {
    return Schedulers.io();
  }

  @Named(MAIN) @Provides public Scheduler provideUIScheduler() {
    return AndroidSchedulers.mainThread();
  }
}
