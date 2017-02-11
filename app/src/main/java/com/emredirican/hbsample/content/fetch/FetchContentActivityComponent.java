package com.emredirican.hbsample.content.fetch;

import com.emredirican.hbsample.ActivityComponent;
import com.emredirican.hbsample.PerActivity;
import dagger.Subcomponent;

@PerActivity @Subcomponent(
    modules = {
        FetchContentActivityModule.class
    }) public interface FetchContentActivityComponent
    extends ActivityComponent<FetchContentActivity> {
}
