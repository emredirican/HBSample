package com.emredirican.hbsample;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import dagger.Module;
import dagger.Provides;

@Module public class InstrumentationModule {

  @Provides public Instrumentation provideInstrumentation() {
    return InstrumentationRegistry.getInstrumentation();
  }
}
