package com.emredirican.hbsample;

import com.emredirican.hbsample.content.Content;
import com.emredirican.hbsample.content.ContentInteractor;
import com.emredirican.hbsample.content.ContentService;
import com.emredirican.hbsample.content.ContentViewState;
import com.emredirican.hbsample.content.Response;
import com.emredirican.hbsample.util.rest.EndPoints;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContentInteractorTest {

  private ContentService contentService;
  private Gson gson;
  private Content.Interactor interactor;
  private List<Response> responseModel;
  private TestScheduler subscribingScheduler;
  private TestObserver<ContentViewState> testObserver;

  @Before public void setUp() throws Exception {

    gson = new Gson();
    contentService = mock(ContentService.class);
    Type responseType = new TypeToken<List<Response>>() {
    }.getType();
    responseModel = ReadResponseFileUtil.readResponse(getClass().getClassLoader(), responseType,
        "fetch_content_success.json", gson);
    when(contentService.remainingSeconds()).thenReturn(Observable.just(responseModel));

    subscribingScheduler = new TestScheduler();
    interactor = new ContentInteractor(contentService, subscribingScheduler);
  }

  @After public void tearDown() throws Exception {

  }

  @Test public void contentUrl() throws Exception {
    HttpUrl baseUrl = HttpUrl.parse("http://private-430377-timing.apiary-mock.com/");
    System.out.println("Base URL: " + baseUrl);
    HttpUrl resolved = baseUrl.resolve("/remaining_seconds");
    System.out.println("Resolved URL: " + resolved);
    assertThat(
        resolved.toString(), is("http://private-430377-timing.apiary-mock.com/remaining_seconds"));
  }

  //understand and assert the response model
  @Test public void contentService_remainingSeconds() throws Exception {
    OkHttpClient okHttpClient =
        new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor()).build();
    Retrofit retrofit = new Retrofit.Builder().baseUrl(EndPoints.CONTENT)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build();
    contentService = retrofit.create(ContentService.class);

    TestObserver<List<Response>> testObserver = contentService.remainingSeconds().test();

    for (Throwable t : testObserver.errors()) {
      t.printStackTrace();
    }
    testObserver.assertComplete();
  }

  @Test public void remainingSeconds_Success() throws Exception {

    testObserver = interactor.remainingSeconds().test();
    triggerSchedulers();

    testObserver.awaitTerminalEvent();
    testObserver.assertNoErrors();
    testObserver.assertComplete();
    List<ContentViewState> contentViewStates = testObserver.values();
    assertThat(contentViewStates.get(0), instanceOf(ContentViewState.Loading.class));
    assertThat(contentViewStates.get(1), instanceOf(ContentViewState.Completed.class));
    assertThat(((ContentViewState.Completed) contentViewStates.get(1)).responseList,
        is(responseModel));
  }

  @Test public void remainingSeconds_Error() throws Exception {
    IOException exception = new IOException("error");
    when(contentService.remainingSeconds()).thenReturn(Observable.<List<Response>>error(exception));

    testObserver = interactor.remainingSeconds().test();
    triggerSchedulers();

    testObserver.awaitTerminalEvent();
    testObserver.assertComplete();
    testObserver.assertNoErrors();
    List<ContentViewState> contentViewStates = testObserver.values();
    assertThat(contentViewStates.get(0), instanceOf(ContentViewState.Loading.class));
    assertThat(contentViewStates.get(1), instanceOf(ContentViewState.Error.class));
    assertThat(((ContentViewState.Error) contentViewStates.get(1)).throwable,
        CoreMatchers.<Throwable>is(exception));
  }

  @Test public void remainingSeconds_run_with_given_schedulers() throws Exception {

    testObserver = interactor.remainingSeconds().test();

    assertOnlyLoadingEmitted(testObserver);

    subscribingScheduler.triggerActions();

    testObserver.assertComplete();
  }

  private void assertOnlyLoadingEmitted(TestObserver<ContentViewState> testObserver) {
    assertThat(testObserver.values().size(), is(1));
    assertThat(testObserver.values().get(0), instanceOf(ContentViewState.Loading.class));
  }

  private void triggerSchedulers() {
    subscribingScheduler.triggerActions();
  }
}
