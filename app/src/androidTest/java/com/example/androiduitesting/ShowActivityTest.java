package com.example.androiduitesting;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule =
            new ActivityScenarioRule<>(MainActivity.class);

    private void addCity(String name) {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText(name));
        onView(withId(R.id.button_confirm)).perform(click());
    }

    private void openFirstCity() {
        onData(anything()).inAdapterView(withId(R.id.city_list))
                .atPosition(0).perform(click());
    }

    @Test
    public void switchesToShowActivityOnClick() {
        addCity("Edmonton");
        openFirstCity();
        onView(withId(R.id.text_city)).check(matches(isDisplayed()));
    }

    @Test
    public void showsSelectedCityName() {
        addCity("Edmonton");
        openFirstCity();
        onView(withId(R.id.text_city)).check(matches(withText("Edmonton")));
    }

    @Test
    public void backButtonReturnsToMain() {
        addCity("Edmonton");
        openFirstCity();
        onView(withId(R.id.button_back)).perform(click());
        // An element unique to MainActivity should be visible again:
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
        // (Alternatively: pressBack(); then assert the same)
        // pressBack();
        // onView(withId(R.id.button_add)).check(matches(isDisplayed()));
    }
}