package com.emredirican.hbsample.content;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.emredirican.hbsample.ApplicationComponent;
import com.emredirican.hbsample.SampleApplication;

public abstract class ContentActivity extends AppCompatActivity {

  @Override protected void onCreate(
      Bundle savedInstanceState
  ) {
    super.onCreate(savedInstanceState);
    injectMembers(((SampleApplication) getApplication()).getApplicationComponent());
  }

  protected abstract void injectMembers(
      ApplicationComponent applicationComponent
  );
}
