package com.emredirican.hbsample.content.display;

import com.emredirican.hbsample.ActivityComponent;
import com.emredirican.hbsample.PerActivity;
import dagger.Subcomponent;

@PerActivity @Subcomponent(
    modules = {
        DisplayContentActivityModule.class
    }) public interface DisplayContentActivityComponent
    extends ActivityComponent<DisplayContentActivity> {

}
