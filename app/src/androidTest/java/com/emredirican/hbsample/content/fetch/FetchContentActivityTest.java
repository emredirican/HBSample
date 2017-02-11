package com.emredirican.hbsample.content.fetch;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import com.emredirican.hbsample.R;
import com.emredirican.hbsample.content.display.DisplayContentActivity;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.emredirican.hbsample.ContentInteractorTestModule.response;
import static org.hamcrest.core.AllOf.allOf;

public class FetchContentActivityTest {

  @Rule public IntentsTestRule<FetchContentActivity> testRule =
      new IntentsTestRule<>(FetchContentActivity.class, true, false);

  private Instrumentation instrumentation;
  private Gson gson = new Gson();

  @Before public void setUp() throws Exception {
    instrumentation = InstrumentationRegistry.getInstrumentation();
  }

  @Test public void fetchData_And_NavigateToContentActivity() throws Exception {
    FetchContentActivity fetchContentActivity = testRule.launchActivity(new Intent());
    intending(hasComponent(DisplayContentActivity.class.getName())).respondWith(
        new Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null));

    clickOnFetchButton();

    intended(allOf(
        hasComponent(DisplayContentActivity.class.getName()),
        hasExtra(DisplayContentActivity.DATA, gson.toJson(response(instrumentation.getContext())))));
  }

  private static void clickOnFetchButton() {
    onView(withId(R.id.btn_fetch)).perform(click());
  }
}
