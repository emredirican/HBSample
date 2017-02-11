package com.emredirican.hbsample;

import android.app.Activity;

public interface ActivityComponent<A extends Activity> {
  void inject(A activity);
}
