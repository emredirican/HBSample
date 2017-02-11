package com.emredirican.hbsample.content;

import java.util.List;

public interface ContentViewState {

  void accept(ViewStateVisitor viewStateVisitor);

  final class Loading implements ContentViewState {

    @Override public void accept(ViewStateVisitor viewStateVisitor) {
      viewStateVisitor.visit(this);
    }
  }

  final class Completed implements ContentViewState {
    public final List<Response> responseList;

    public Completed(List<Response> responseList) {
      this.responseList = responseList;
    }

    @Override public void accept(ViewStateVisitor viewStateVisitor) {
      viewStateVisitor.visit(this);
    }
  }

  final class Error implements ContentViewState {
    public final Throwable throwable;

    public Error(Throwable throwable) {
      this.throwable = throwable;
    }

    @Override public void accept(ViewStateVisitor viewStateVisitor) {
      viewStateVisitor.visit(this);
    }
  }
}
