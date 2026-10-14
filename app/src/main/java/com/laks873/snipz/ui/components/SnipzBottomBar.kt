package com.laks873.snipz.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.laks873.snipz.R

// The four tabs, each with its icon and a name for screen readers
enum class SnipzTab(val iconRes: Int, val label: String) {
    Home(R.drawable.ic_home, "Home"),
    Subscriptions(R.drawable.ic_subscriptions, "Subscriptions"),
    Discover(R.drawable.ic_discover, "Discover"),
    Analytics(R.drawable.ic_analytics, "Analytics")
}

@Composable
fun SnipzBottomBar(
    selectedTab: SnipzTab,
    onTabSelected: (SnipzTab) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 0.dp
    ) {
        SnipzTab.entries.forEach { tab ->
            NavigationBarItem(
                selected = tab == selectedTab,
                onClick = { onTabSelected(tab) },
                icon = { Icon(painterResource(tab.iconRes), contentDescription = tab.label) },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,   // white icon on the pill
                    unselectedIconColor = MaterialTheme.colorScheme.primary,   // green icons
                    indicatorColor = MaterialTheme.colorScheme.primary         // the green pill
                )
            )
        }
    }
}

