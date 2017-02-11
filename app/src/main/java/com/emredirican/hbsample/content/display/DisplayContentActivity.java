package com.emredirican.hbsample.content.display;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.emredirican.hbsample.ApplicationComponent;
import com.emredirican.hbsample.R;
import com.emredirican.hbsample.content.Content;
import com.emredirican.hbsample.content.ContentActivity;
import com.emredirican.hbsample.content.ContentViewState;
import com.emredirican.hbsample.content.Response;
import com.emredirican.hbsample.content.Second;
import com.emredirican.hbsample.content.ViewStateVisitor;
import com.emredirican.hbsample.util.rest.RestServicesModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import timber.log.Timber;

public class DisplayContentActivity extends ContentActivity implements Content.View {
  public static final String DATA = "data";

  @Inject Content.Presenter presenter;
  @Inject ViewStateVisitor viewStateVisitor;
  @Inject @Named("ForRest") Gson gson;
  @Inject @Named("Main") Scheduler schedulerMain;

  @BindView(R.id.rv_display_content) RecyclerView recyclerView;

  private DisplayContentActivityComponent component;
  private SecondsViewAdapter secondsViewAdapter;
  private DisposableObserver<Long> timerObserver;

  @Override protected void injectMembers(ApplicationComponent applicationComponent) {
    component =
        applicationComponent.displayContentComponent(new DisplayContentActivityModule(this));
    component.inject(this);
  }

  public static Intent createIntent(Context context, List<Response> responseList) {
    Intent intent = new Intent(context, DisplayContentActivity.class);
    Gson gson = new RestServicesModule().provideGsonForRest();
    intent.putExtra(DATA, gson.toJson(responseList));
    return intent;
  }

  @Override public void onCreate(
      Bundle savedInstanceState
  ) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_content);
    ButterKnife.bind(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

    initRecyclerView();
  }

  private void initRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    Type type = new TypeToken<List<Response>>() {
    }.getType();
    List<Response> responseList = gson.fromJson(getIntent().getStringExtra(DATA), type);
    secondsViewAdapter = new SecondsViewAdapter(responseList.get(0).seconds);
    recyclerView.setAdapter(secondsViewAdapter);
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.attachView(this);
    startTimer();
  }

  private void startTimer() {
    timerObserver = new DisposableObserver<Long>() {

      @Override public void onNext(Long value) {
        List<Second> secondList = secondsViewAdapter.dataListSnapshot();
        for (Second second : secondList) {
          second.value -= 1;
          if (second.value == 0) {
            presenter.update();
            break;
          }
        }
        secondsViewAdapter.update(secondList);
      }

      @Override public void onError(Throwable e) {
        Timber.e(e.getMessage());
      }

      @Override public void onComplete() {
        //nothing to do
      }
    };
    Observable.interval(1, 1, TimeUnit.SECONDS).observeOn(schedulerMain).subscribe(timerObserver);
  }

  @Override protected void onPause() {
    timerObserver.dispose();
    presenter.detachView();
    super.onPause();
  }

  @Override public void render(ContentViewState viewState) {
    viewState.accept(viewStateVisitor);
  }

  public void updateWithNew(List<Response> responses) {
    secondsViewAdapter.update(responses.get(0).seconds);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      NavUtils.navigateUpFromSameTask(this);
    }
    return super.onOptionsItemSelected(item);
  }

  @VisibleForTesting SecondsViewAdapter getSecondsViewAdapter() {
    return secondsViewAdapter;
  }
}
