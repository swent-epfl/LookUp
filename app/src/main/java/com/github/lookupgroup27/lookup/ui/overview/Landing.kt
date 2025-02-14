package com.github.lookupgroup27.lookup.ui.overview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.github.lookupgroup27.lookup.R
import com.github.lookupgroup27.lookup.ui.navigation.NavigationActions
import com.github.lookupgroup27.lookup.ui.navigation.Screen

/**
 * LandingScreen displays the main landing page of the app. It includes a background image, logo,
 * and a button with a home icon. The home button navigates to the "Menu" screen and the background
 * is clickable to navigate to the "Map" screen.
 *
 * @param navigationActions Instance of NavigationActions to handle navigation between screens.
 */
@Composable
fun LandingScreen(navigationActions: NavigationActions) {
  Box(
      modifier = Modifier.fillMaxSize().clickable { navigationActions.navigateTo(Screen.MAP) },
      contentAlignment = Alignment.Center) {

        // Background Image
        Image(
            painter = painterResource(id = R.drawable.landing_screen_bckgrnd),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize())

        // Center logo image
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
              // Logo Image
              Image(
                  painter = painterResource(id = R.drawable.app_logo),
                  contentDescription = "Look Up Logo",
                  modifier = Modifier.size(250.dp),
                  contentScale = ContentScale.Fit)
            }

        // Home Button at the Bottom
        Box(
            modifier = Modifier.fillMaxSize().padding(bottom = 32.dp),
            contentAlignment = Alignment.BottomCenter) {
              Button(
                  onClick = { navigationActions.navigateTo(Screen.MENU) },
                  modifier =
                      Modifier.padding(16.dp) // Padding around the button
                          .size(150.dp) // Total button size
                          .testTag("Home Icon"),
                  shape = CircleShape, // Button is circular
                  colors =
                      ButtonDefaults.buttonColors(
                          containerColor = Color.Transparent) // Make the background transparent
                  ) {
                    Icon(
                        painter = painterResource(id = R.drawable.home_button),
                        contentDescription = "Home Icon",
                        tint = Color.Unspecified, // Keep the original icon colors
                        modifier = Modifier.fillMaxSize() // Icon fills the button completely
                        )
                  }
            }
        // Click for full map view prompt
        Text(
            text = "Click for full map view",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 48.dp))
      }
}

@Preview(showBackground = true)
@Composable
fun PreviewLandingScreen() {
  LandingScreen(NavigationActions(rememberNavController()))
}
