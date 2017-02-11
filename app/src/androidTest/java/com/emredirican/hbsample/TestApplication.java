package com.emredirican.hbsample;

public class TestApplication extends SampleApplication {

  @Override public ApplicationComponent buildComponent() {
    return ActivityTestComponent.Initializer.init(this);
  }

}
