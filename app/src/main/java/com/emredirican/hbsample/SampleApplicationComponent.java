package com.emredirican.hbsample;

import com.emredirican.hbsample.content.ContentInteractorModule;
import com.emredirican.hbsample.content.ContentPresenterModule;
import com.emredirican.hbsample.content.ContentServiceModule;
import com.emredirican.hbsample.util.rest.RestServicesModule;
import com.emredirican.hbsample.util.scheduler.SchedulerModule;
import dagger.Component;

@PerApplication @Component(modules = {
    ApplicationModule.class, ContentPresenterModule.class, ContentInteractorModule.class,
    RestServicesModule.class, ContentServiceModule.class, SchedulerModule.class
}) public interface SampleApplicationComponent extends ApplicationComponent {

  final class Initializer {
    private Initializer() {
    }

    public static SampleApplicationComponent init(SampleApplication application) {
      return DaggerSampleApplicationComponent.builder()
          .applicationModule(new ApplicationModule(application))
          .build();
    }
  }
}
