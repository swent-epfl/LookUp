package com.github.lookupgroup27.lookup.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationMenu(
    onTabSelect: (TopLevelDestination) -> Unit,
    tabList: List<TopLevelDestination>,
    selectedItem: String
) {
  NavigationBar(
      modifier = Modifier.fillMaxWidth().height(60.dp).testTag("bottomNavigationMenu"),
      containerColor = Color(0xFF0D1023),
      content = {
        tabList.forEach { tab ->
          NavigationBarItem(
              icon = { Icon(tab.icon, contentDescription = null, tint = Color.White) },
              label = { Text(text = tab.textId, color = Color.White) },
              selected = tab.route == selectedItem,
              onClick = { onTabSelect(tab) },
              modifier = Modifier.clip(shape = RoundedCornerShape(50.dp)).testTag(tab.textId))
        }
      },
  )
}
