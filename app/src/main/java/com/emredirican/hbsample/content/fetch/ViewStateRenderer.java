package com.emredirican.hbsample.content.fetch;

import com.emredirican.hbsample.PerActivity;
import com.emredirican.hbsample.content.ContentActivity;
import com.emredirican.hbsample.content.ContentViewState;
import com.emredirican.hbsample.content.ViewStateVisitor;
import com.emredirican.hbsample.content.display.DisplayContentActivity;
import javax.inject.Inject;
import timber.log.Timber;

@PerActivity public class ViewStateRenderer implements ViewStateVisitor {

  private final ContentActivity activity;

  @Inject public ViewStateRenderer(ContentActivity activity) {
    this.activity = activity;
  }

  @Override public void visit(ContentViewState.Loading viewState) {
    Timber.d(viewState.getClass().getName());
  }

  @Override public void visit(ContentViewState.Completed viewState) {
    activity.startActivity(DisplayContentActivity.createIntent(activity, viewState.responseList));
  }

  @Override public void visit(ContentViewState.Error viewState) {
    Timber.e(viewState.throwable.getMessage());
  }
}
