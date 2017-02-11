package com.emredirican.hbsample;

import com.emredirican.hbsample.content.display.DisplayContentActivityComponent;
import com.emredirican.hbsample.content.display.DisplayContentActivityModule;
import com.emredirican.hbsample.content.fetch.FetchContentActivityComponent;
import com.emredirican.hbsample.content.fetch.FetchContentActivityModule;
import dagger.MembersInjector;

public interface ApplicationComponent extends MembersInjector<SampleApplication> {

  FetchContentActivityComponent fetchContentComponent(FetchContentActivityModule module);

  DisplayContentActivityComponent displayContentComponent(
      DisplayContentActivityModule module
  );
}
