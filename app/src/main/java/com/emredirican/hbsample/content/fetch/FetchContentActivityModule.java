package com.emredirican.hbsample.content.fetch;

import com.emredirican.hbsample.ActivityModule;
import com.emredirican.hbsample.PerActivity;
import com.emredirican.hbsample.content.ViewStateVisitor;
import dagger.Module;
import dagger.Provides;

@Module public class FetchContentActivityModule extends ActivityModule<FetchContentActivity> {

  public FetchContentActivityModule(FetchContentActivity activity) {
    super(activity);
  }

  @Provides @PerActivity ViewStateVisitor provideViewStatVisitor() {
    return new ViewStateRenderer(activity);
  }
}
