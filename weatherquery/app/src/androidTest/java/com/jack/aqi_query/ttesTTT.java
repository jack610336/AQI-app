package com.jack.aqi_query;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ttesTTT {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ttesTTT() {

        for(int i=1;i<24;i++){
            ViewInteraction appCompatImageButton = onView(
                    allOf(withContentDescription("Open Navigation"),
                            childAtPosition(
                                    allOf(withId(R.id.ToolBar),
                                            childAtPosition(
                                                    withClassName(is("android.widget.LinearLayout")),
                                                    0)),
                                    1),
                            isDisplayed()));
            appCompatImageButton.perform(click());

            onView(withId(R.id.design_navigation_view))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
        }




        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.swipe_refresh),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));
        viewGroup.perform(swipeDown());

        try {
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
