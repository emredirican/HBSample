package com.emredirican.hbsample.content.display;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import com.emredirican.hbsample.ContentInteractorTestModule;
import com.emredirican.hbsample.R;
import java.io.IOException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;

public class DisplayContentActivityTest {

  public static final int DATA_LIST_SIZE = 8;
  public static final int SEC_MILLIS = 1000;

  @Rule public IntentsTestRule<DisplayContentActivity> testRule =
      new IntentsTestRule<>(DisplayContentActivity.class, true, false);

  private Instrumentation instrumentation;

  @Before public void setUp() throws Exception {

    instrumentation = InstrumentationRegistry.getInstrumentation();
  }

  @Test public void launch() throws Exception {
    DisplayContentActivity activity = launchDisplayContentActivity();

    onView(withId(R.id.rv_display_content)).check(matches(isDisplayed()));
    assertThat(activity.getSecondsViewAdapter().dataListSnapshot().size(), is(DATA_LIST_SIZE));
  }

  private DisplayContentActivity launchDisplayContentActivity() throws IOException {
    Intent intent = DisplayContentActivity.createIntent(
        instrumentation.getContext(),
        ContentInteractorTestModule.response(instrumentation.getContext()));
    return testRule.launchActivity(intent);
  }

  @Test public void countDown() throws Exception {
    DisplayContentActivity activity = launchDisplayContentActivity();

    int value = activity.getSecondsViewAdapter().dataListSnapshot().get(0).value;
    Thread.sleep(SEC_MILLIS);
    int newValue = activity.getSecondsViewAdapter().dataListSnapshot().get(0).value;
    assertThat(value, is(newValue + 1));

    //TODO need to think of a way to check the numbers are displayed as well
  }
}
