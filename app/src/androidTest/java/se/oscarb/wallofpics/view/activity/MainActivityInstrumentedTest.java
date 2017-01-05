package se.oscarb.wallofpics.view.activity;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import se.oscarb.wallofpics.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityInstrumentedTest {

    // The activity to be launched before each test
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateSearchEditText() {
        onView(withId(R.id.query)).perform(typeText("Cats")).check(matches(withText("Cats")));
    }

}
