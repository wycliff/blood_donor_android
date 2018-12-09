package wycliffe.com.bdf.activity;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import tools.fastlane.screengrab.Screengrab;
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy;
import tools.fastlane.screengrab.locale.LocaleTestRule;
import wycliffe.com.bdf.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @ClassRule
    public static final LocaleTestRule localeTestRule = new LocaleTestRule();

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void homeActivityTest() {

        Screengrab.setDefaultScreenshotStrategy(new UiAutomatorScreenshotStrategy());
        Screengrab.screenshot("before_anything");

        ViewInteraction appCompatEditText = onView(
allOf(withId(R.id.etEmail),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText.perform(replaceText("mam"), closeSoftKeyboard());
        Screengrab.screenshot("name_entered_attempt");

        ViewInteraction appCompatEditText2 = onView(
allOf(withId(R.id.etEmail), withText("mam"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText2.perform(click());

        pressBack();

        ViewInteraction appCompatEditText3 = onView(
allOf(withId(R.id.etEmail), withText("mam"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
allOf(withId(R.id.etEmail), withText("mam"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText4.perform(click());

        ViewInteraction appCompatEditText5 = onView(
allOf(withId(R.id.etEmail), withText("mam"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText5.perform(replaceText("ma"));

        ViewInteraction appCompatEditText6 = onView(
allOf(withId(R.id.etEmail), withText("ma"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText6.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
allOf(withId(R.id.etEmail), withText("ma"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText7.perform(click());

        ViewInteraction appCompatEditText8 = onView(
allOf(withId(R.id.etEmail), withText("ma"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText8.perform(replaceText("martinmogusu@ggmail"));

        ViewInteraction appCompatEditText9 = onView(
allOf(withId(R.id.etEmail), withText("martinmogusu@ggmail"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText9.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
allOf(withId(R.id.etEmail), withText("martinmogusu@ggmail"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText10.perform(click());

        ViewInteraction appCompatEditText11 = onView(
allOf(withId(R.id.etEmail), withText("martinmogusu@ggmail"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText11.perform(replaceText("martinmogusu@gmail"));

        ViewInteraction appCompatEditText12 = onView(
allOf(withId(R.id.etEmail), withText("martinmogusu@gmail"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText12.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText13 = onView(
allOf(withId(R.id.etEmail), withText("martinmogusu@gmail"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText13.perform(click());

        ViewInteraction appCompatEditText14 = onView(
allOf(withId(R.id.etEmail), withText("martinmogusu@gmail"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText14.perform(replaceText("martinmogusu@gmail.com"));

        ViewInteraction appCompatEditText15 = onView(
allOf(withId(R.id.etEmail), withText("martinmogusu@gmail.com"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_email),
0),
0),
isDisplayed()));
        appCompatEditText15.perform(closeSoftKeyboard());
        Screengrab.screenshot("email_entered");

        ViewInteraction appCompatEditText16 = onView(
allOf(withId(R.id.etPassword),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_password),
0),
0),
isDisplayed()));
        appCompatEditText16.perform(replaceText("This"), closeSoftKeyboard());

        ViewInteraction appCompatEditText17 = onView(
allOf(withId(R.id.etPassword), withText("This"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_password),
0),
0),
isDisplayed()));
        Screengrab.screenshot("password_entered");
        appCompatEditText17.perform(click());
        Screengrab.screenshot("password_entered");


        ViewInteraction appCompatEditText18 = onView(
allOf(withId(R.id.etPassword), withText("This"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_password),
0),
0),
isDisplayed()));
        appCompatEditText18.perform(click());

        ViewInteraction appCompatEditText19 = onView(
allOf(withId(R.id.etPassword), withText("This"),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_password),
0),
0),
isDisplayed()));
        appCompatEditText19.perform(replaceText(""));

        ViewInteraction appCompatEditText20 = onView(
allOf(withId(R.id.etPassword),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_password),
0),
0),
isDisplayed()));
        appCompatEditText20.perform(closeSoftKeyboard());
        Screengrab.screenshot("password_attempt");

        ViewInteraction appCompatEditText21 = onView(
allOf(withId(R.id.etPassword),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_password),
0),
0),
isDisplayed()));
        appCompatEditText21.perform(click());

        ViewInteraction appCompatEditText22 = onView(
allOf(withId(R.id.etPassword),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_password),
0),
0),
isDisplayed()));
        appCompatEditText22.perform(click());

        ViewInteraction appCompatEditText23 = onView(
allOf(withId(R.id.etPassword),
childAtPosition(
childAtPosition(
withId(R.id.input_layout_password),
0),
0),
isDisplayed()));
        appCompatEditText23.perform(replaceText("12345678999"), closeSoftKeyboard());

        Screengrab.screenshot("password_entered");


        ViewInteraction appCompatCheckBox = onView(
allOf(withId(R.id.showPassword), withText("Show Password"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.FrameLayout")),
0),
2)));
        appCompatCheckBox.perform(scrollTo(), click());

        ViewInteraction appCompatCheckBox2 = onView(
allOf(withId(R.id.showPassword), withText("Show Password"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.FrameLayout")),
0),
2)));
        appCompatCheckBox2.perform(scrollTo(), click());

        ViewInteraction appCompatButton = onView(
allOf(withId(R.id.buttonLogin), withText("LOGIN"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.FrameLayout")),
0),
3)));
        appCompatButton.perform(scrollTo());

//        ViewInteraction appCompatButton2 = onView(
//allOf(withId(R.id.recommendButton), withText("Generate"),
//childAtPosition(
//childAtPosition(
//withClassName(is("android.widget.FrameLayout")),
//0),
//0),
//isDisplayed()));
//        appCompatButton2.perform(click());

//        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
//
//        ViewInteraction appCompatTextView = onView(
//allOf(withId(R.id.title), withText("Log out"),
//childAtPosition(
//childAtPosition(
//withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
//0),
//0),
//isDisplayed()));
//        appCompatTextView.perform(click());
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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
