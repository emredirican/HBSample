package com.emredirican.hbsample.content.display;

import com.emredirican.hbsample.content.ContentViewState;
import com.emredirican.hbsample.content.ViewStateVisitor;
import timber.log.Timber;

public class ViewStateRenderer implements ViewStateVisitor {

  private final DisplayContentActivity activity;

  public ViewStateRenderer(DisplayContentActivity activity) {
    this.activity = activity;
  }

  @Override public void visit(ContentViewState.Loading viewState) {
    Timber.d(viewState.getClass().getName());
  }

  @Override public void visit(ContentViewState.Completed viewState) {
    activity.updateWithNew(viewState.responseList);
  }

  @Override public void visit(ContentViewState.Error viewState) {
    Timber.e(viewState.throwable.getMessage());
  }
}
