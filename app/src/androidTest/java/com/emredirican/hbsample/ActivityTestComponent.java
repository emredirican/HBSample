package com.emredirican.hbsample;

import dagger.Component;

@PerApplication @Component(modules = {
    ApplicationModule.class, ContentInteractorTestModule.class, InstrumentationModule.class,
    ContentPresenterTestModule.class, SchedulersTestModule.class
}) public interface ActivityTestComponent extends ApplicationComponent {

  final class Initializer {
    private Initializer() {
    }

    public static ActivityTestComponent init(SampleApplication application) {
      return DaggerActivityTestComponent.builder()
          .applicationModule(new ApplicationModule(application))
          .build();
    }
  }
}
