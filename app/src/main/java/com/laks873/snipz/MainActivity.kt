package com.laks873.snipz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.laks873.snipz.ui.components.SnipzBottomBar
import com.laks873.snipz.ui.components.SnipzTab
import com.laks873.snipz.ui.screens.HomeScreen
import com.laks873.snipz.ui.screens.sampleOffers
import com.laks873.snipz.ui.screens.sampleSubscriptions
import com.laks873.snipz.ui.theme.SnipzTheme
import com.laks873.snipz.ui.screens.SubscriptionsScreen
import com.laks873.snipz.ui.screens.sampleMonthlySpend

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnipzTheme {
                var selectedTab by remember { mutableStateOf(SnipzTab.Home) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        SnipzBottomBar(
                            selectedTab = selectedTab,
                            onTabSelected = { selectedTab = it }
                        )
                    }
                ) { innerPadding ->
                    when (selectedTab) {
                        SnipzTab.Home -> HomeScreen(
                            userName = "Lakshya",
                            offers = sampleOffers,
                            subscriptions = sampleSubscriptions.filter { it.isActive },
                            modifier = Modifier.padding(innerPadding)
                        )
                        SnipzTab.Subscriptions -> SubscriptionsScreen(
                            subscriptions = sampleSubscriptions,
                            monthlySpend = sampleMonthlySpend,
                            modifier = Modifier.padding(innerPadding)
                        )
                        else -> Box(
                            modifier = Modifier.fillMaxSize().padding(innerPadding),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("${selectedTab.label}: coming soon")
                        }
                    }
                }
            }
        }
    }
}