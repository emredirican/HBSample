package com.emredirican.hbsample.content;

public interface ViewStateVisitor {

  void visit(ContentViewState.Loading viewState);

  void visit(ContentViewState.Completed viewState);

  void visit(ContentViewState.Error viewState);
}
