package com.github.lookupgroup27.lookup.overview

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.github.lookupgroup27.lookup.ui.navigation.*
import com.github.lookupgroup27.lookup.ui.overview.MenuScreen
import org.junit.*
import org.mockito.kotlin.*

class MenuScreenTest {

  @get:Rule val composeTestRule = createComposeRule()

  private val mockNavigationActions: NavigationActions = mock()

  @Test
  fun menuScreen_displaysWelcomeText() {
    composeTestRule.setContent { MenuScreen(navigationActions = mockNavigationActions) }

    // Check that the "Welcome !" text is displayed
    composeTestRule.onNodeWithText("Welcome !").assertIsDisplayed()
  }

  @Test
  fun menuScreen_backButton_navigatesBack() {
    composeTestRule.setContent { MenuScreen(navigationActions = mockNavigationActions) }

    // Perform click on the back button
    composeTestRule.onNodeWithTag("back_button").performClick()

    // Verify navigation back action is triggered
    verify(mockNavigationActions).goBack()
  }

  @Test
  fun menuScreen_displaysAllButtons() {
    composeTestRule.setContent { MenuScreen(navigationActions = mockNavigationActions) }

    // Check that all buttons are displayed
    composeTestRule.onNodeWithText("Quizzes").assertIsDisplayed()
    composeTestRule.onNodeWithText("Calendar").assertIsDisplayed()
    composeTestRule.onNodeWithText("Sky Tracker").assertIsDisplayed()
  }

  @Test
  fun menuScreen_clickQuizzes_navigatesToQuizScreen() {
    composeTestRule.setContent { MenuScreen(navigationActions = mockNavigationActions) }

    // Perform click on "Quizzes" button
    composeTestRule.onNodeWithText("Quizzes").performClick()

    // Verify navigation to Quiz screen is triggered
    verify(mockNavigationActions).navigateTo(Screen.QUIZ)
  }

  @Test
  fun menuScreen_clickCalendar_navigatesToCalendarScreen() {
    composeTestRule.setContent { MenuScreen(navigationActions = mockNavigationActions) }

    // Perform click on "Calendar" button
    composeTestRule.onNodeWithText("Calendar").performClick()

    // Verify navigation to Calendar screen is triggered
    verify(mockNavigationActions).navigateTo(Screen.CALENDAR)
  }

  @Test
  fun menuScreen_clickSkyTracker_navigatesToSkyTrackerScreen() {
    composeTestRule.setContent { MenuScreen(navigationActions = mockNavigationActions) }

    // Perform click on "Sky Tracker" button
    composeTestRule.onNodeWithText("Sky Tracker").performClick()

    // Verify navigation to Sky Tracker screen is triggered
    verify(mockNavigationActions).navigateTo(Screen.SKY_TRACKER)
  }
}
