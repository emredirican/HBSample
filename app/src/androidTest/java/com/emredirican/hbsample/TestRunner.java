package com.emredirican.hbsample;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

public class TestRunner extends AndroidJUnitRunner {

  @Override public Application newApplication(
      ClassLoader cl, String className, Context context
  ) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
    return super.newApplication(cl, TestApplication.class.getName(), context);
  }
}
