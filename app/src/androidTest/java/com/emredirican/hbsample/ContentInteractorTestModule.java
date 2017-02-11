package com.emredirican.hbsample;

import android.app.Instrumentation;
import android.content.Context;
import com.emredirican.hbsample.content.Content;
import com.emredirican.hbsample.content.ContentViewState;
import com.emredirican.hbsample.content.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Named;

@Module public class ContentInteractorTestModule {

  @Provides @PerApplication public Content.Interactor provideContentInteractorTestDouble(
      final Instrumentation instrumentation
  ) {
    return new Content.Interactor() {
      @Override public Observable<ContentViewState> remainingSeconds() {
        try {
          return Observable.just(
              new ContentViewState.Completed(response(instrumentation.getContext())))
              .cast(ContentViewState.class);
        } catch (IOException e) {
          e.printStackTrace();
        }
        return Observable.empty();
      }
    };
  }

  public static List<Response> response(Context context) throws IOException {
    Type responseType = new TypeToken<List<Response>>() {
    }.getType();
    BufferedReader reader = new BufferedReader(new InputStreamReader(
        context.getResources().getAssets().open("fetch_content_success.json")));
    return new Gson().fromJson(reader, responseType);
  }

  @Provides @PerApplication @Named("ForRest") public Gson provideGson() {
    return new Gson();
  }
}
