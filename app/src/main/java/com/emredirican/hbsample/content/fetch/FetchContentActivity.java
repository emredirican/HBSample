package com.emredirican.hbsample.content.fetch;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.emredirican.hbsample.ApplicationComponent;
import com.emredirican.hbsample.R;
import com.emredirican.hbsample.content.Content;
import com.emredirican.hbsample.content.ContentActivity;
import com.emredirican.hbsample.content.ContentViewState;
import com.emredirican.hbsample.content.ViewStateVisitor;
import javax.inject.Inject;

public class FetchContentActivity extends ContentActivity implements Content.View{

  @Inject Content.Presenter presenter;
  @Inject ViewStateVisitor viewStateVisitor;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fetch_content);
    ButterKnife.bind(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.attachView(this);
  }

  @Override protected void onPause() {
    presenter.detachView();
    super.onPause();
  }

  @OnClick(R.id.btn_fetch) void onFetchClicked() {
    presenter.update();
  }

  @Override protected void injectMembers(
      ApplicationComponent applicationComponent
  ) {
    applicationComponent.fetchContentComponent(new FetchContentActivityModule(this)).inject(this);
  }

  @Override public void render(ContentViewState viewState) {
    viewState.accept(viewStateVisitor);
  }
}
