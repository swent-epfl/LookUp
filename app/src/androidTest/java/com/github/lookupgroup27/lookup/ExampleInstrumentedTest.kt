package com.github.lookupgroup27.lookup

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.lookupgroup27.lookup.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest : TestCase() {

  @get:Rule val composeTestRule = createAndroidComposeRule<MainActivity>()

  @Ignore
  @Test
  fun test() = run {
    step("Start Main Activity") {
      ComposeScreen.onComposeScreen<MainScreen>(composeTestRule) {
        simpleText {
          assertIsDisplayed()
          assertTextEquals("Hello Android!")
        }
      }
    }
  }
}
