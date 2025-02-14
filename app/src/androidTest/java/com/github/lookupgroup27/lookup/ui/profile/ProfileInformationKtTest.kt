package com.github.lookupgroup27.lookup.ui.profile

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import com.github.lookupgroup27.lookup.model.profile.ProfileRepository
import com.github.lookupgroup27.lookup.model.profile.ProfileViewModel
import com.github.lookupgroup27.lookup.model.profile.UserProfile
import com.github.lookupgroup27.lookup.ui.navigation.NavigationActions
import com.github.lookupgroup27.lookup.ui.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ProfileInformationScreenTest {

  private lateinit var profileRepository: ProfileRepository
  private lateinit var profileViewModel: ProfileViewModel
  private lateinit var navigationActions: NavigationActions
  private lateinit var firebaseAuth: FirebaseAuth

  @get:Rule val composeTestRule = createComposeRule()

  private val mockUserProfile =
      UserProfile(username = "JohnDoe", bio = "This is a bio", email = "john.doe@example.com")

  @Before
  fun setUp() {

    profileRepository = mock(ProfileRepository::class.java)
    profileViewModel = ProfileViewModel(profileRepository)
    navigationActions = mock(NavigationActions::class.java)
    firebaseAuth = mock(FirebaseAuth::class.java)

    // Define navigation action behavior
    `when`(navigationActions.currentRoute()).thenReturn(Screen.PROFILE_INFORMATION)
  }

  @Test
  fun displayAllComponents() {
    composeTestRule.setContent { ProfileInformationScreen(profileViewModel, navigationActions) }

    // Scroll to and check that the main components are displayed
    composeTestRule.onNodeWithTag("editProfileScreen").assertIsDisplayed()
    composeTestRule.onNodeWithTag("editProfileTitle").assertIsDisplayed()
    composeTestRule.onNodeWithTag("goBackButton").assertIsDisplayed()

    composeTestRule.onNodeWithTag("editProfileUsername").performScrollTo().assertIsDisplayed()

    composeTestRule.onNodeWithTag("editProfileEmail").performScrollTo().assertIsDisplayed()

    composeTestRule.onNodeWithTag("editProfileBio").performScrollTo().assertIsDisplayed()

    composeTestRule.onNodeWithTag("profileSaveButton").performScrollTo().assertIsDisplayed()

    composeTestRule.onNodeWithTag("profileLogout").performScrollTo().assertIsDisplayed()

    // Check button texts after scrolling
    composeTestRule.onNodeWithTag("profileSaveButton").assertTextEquals("Save")
    composeTestRule.onNodeWithTag("profileLogout").assertTextEquals("Sign out")
  }

  @Test
  fun saveButtonDisabledWhenFieldsAreEmpty() {
    composeTestRule.setContent { ProfileInformationScreen(profileViewModel, navigationActions) }

    // Initially, all fields are empty, so the save button should be disabled
    composeTestRule.onNodeWithTag("profileSaveButton").assertIsNotEnabled()

    // Fill in username, bio, and email
    composeTestRule.onNodeWithTag("editProfileUsername").performTextInput("JohnDoe")
    composeTestRule.onNodeWithTag("editProfileEmail").performTextInput("john.doe@example.com")
    composeTestRule.onNodeWithTag("editProfileBio").performTextInput("This is a bio")

    // Now all fields are filled, so the save button should be enabled
    composeTestRule.onNodeWithTag("profileSaveButton").assertIsEnabled()
  }

  @Test
  fun saveButtonDisabledWhenAnyFieldIsEmpty() {
    composeTestRule.setContent { ProfileInformationScreen(profileViewModel, navigationActions) }

    // Initially, all fields are empty, so the save button should be disabled
    composeTestRule.onNodeWithTag("profileSaveButton").assertIsNotEnabled()

    // Fill only the username, leave other fields empty
    composeTestRule.onNodeWithTag("editProfileUsername").performTextInput("JohnDoe")
    composeTestRule.onNodeWithTag("profileSaveButton").assertIsNotEnabled()

    // Fill in the email and leave the bio empty
    composeTestRule.onNodeWithTag("editProfileEmail").performTextInput("john.doe@example.com")
    composeTestRule.onNodeWithTag("profileSaveButton").assertIsNotEnabled()

    // Now fill the bio
    composeTestRule.onNodeWithTag("editProfileBio").performTextInput("This is a bio")

    // Now all fields are filled, the save button should be enabled
    composeTestRule.onNodeWithTag("profileSaveButton").assertIsEnabled()
  }

  @Test
  fun saveButtonWorks() {
    composeTestRule.setContent { ProfileInformationScreen(profileViewModel, navigationActions) }

    // Perform click on the save button
    composeTestRule.onNodeWithTag("profileSaveButton").performClick()

    // Mock behavior can be asserted in ViewModel (not shown here, as it's logic dependent)
  }

  @Test
  fun logoutButtonWorks() {
    composeTestRule.setContent { ProfileInformationScreen(profileViewModel, navigationActions) }

    // Scroll to the sign-out button if it's off-screen, then click it
    composeTestRule.onNodeWithTag("profileLogout").performScrollTo().performClick()

    // Verify that the navigation action to the landing screen was triggered
    Mockito.verify(navigationActions).navigateTo(Screen.LANDING)
  }

  @Test
  fun saveButtonDisabledWhenFieldsAreIncomplete() {
    composeTestRule.setContent { ProfileInformationScreen(profileViewModel, navigationActions) }

    // Assert: Save button is initially disabled
    composeTestRule.onNodeWithTag("profileSaveButton").assertIsNotEnabled()

    // Act: Fill in only the username
    composeTestRule.onNodeWithTag("editProfileUsername").performTextInput("JohnDoe")
    // Assert: Save button is still disabled
    composeTestRule.onNodeWithTag("profileSaveButton").assertIsNotEnabled()

    // Act: Fill in email
    composeTestRule.onNodeWithTag("editProfileEmail").performTextInput("john.doe@example.com")
    // Assert: Save button is still disabled because bio is empty
    composeTestRule.onNodeWithTag("profileSaveButton").assertIsNotEnabled()

    // Act: Fill in the bio
    composeTestRule.onNodeWithTag("editProfileBio").performTextInput("This is a bio")
    // Assert: Save button should now be enabled
    composeTestRule.onNodeWithTag("profileSaveButton").assertIsEnabled()
  }

  @Test
  fun saveButtonDisabledWhenAllFieldsAreEmpty() {
    composeTestRule.setContent { ProfileInformationScreen(profileViewModel, navigationActions) }

    // Assert: Save button is disabled because no fields have been populated
    composeTestRule.onNodeWithTag("profileSaveButton").assertIsNotEnabled()
  }
}
