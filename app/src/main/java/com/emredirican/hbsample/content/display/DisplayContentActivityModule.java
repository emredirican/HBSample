package com.emredirican.hbsample.content.display;

import com.emredirican.hbsample.ActivityModule;
import com.emredirican.hbsample.PerActivity;
import com.emredirican.hbsample.content.ViewStateVisitor;
import dagger.Module;
import dagger.Provides;

@Module public class DisplayContentActivityModule extends ActivityModule<DisplayContentActivity> {

  public DisplayContentActivityModule(DisplayContentActivity activity) {
    super(activity);
  }

  @Provides @PerActivity public ViewStateVisitor provideViewStateVisitor() {
    return new ViewStateRenderer(activity);
  }
}
