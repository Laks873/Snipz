package com.laks873.snipz

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laks873.snipz.ui.components.OfferCard
import com.laks873.snipz.ui.components.SnipzBottomBar
import com.laks873.snipz.ui.components.SnipzTab
import com.laks873.snipz.ui.components.SubscriptionCard
import com.laks873.snipz.ui.theme.SnipzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnipzTheme {
                // Remembers which tab is selected. When it changes, the screen redraws.
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
                        SnipzTab.Home -> ThemeCheck(modifier = Modifier.padding(innerPadding))
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

@Composable
fun ThemeCheck(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Hi! Lakshya", style = MaterialTheme.typography.headlineMedium)
        Text("New offers", style = MaterialTheme.typography.titleMedium)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            item { OfferCard(name = "Photoshop", price = "₹1,676", backgroundColor = Color(0xFF0A1F44), badge = "Ps") }
            item { OfferCard(name = "JioHotstar", price = "₹149", backgroundColor = Color(0xFF0F1014), badge = "JH") }
            item { OfferCard(name = "Canva Pro", price = "₹500", backgroundColor = Color(0xFF5B2BC9), badge = "Ca") }
        }
        Text("My Subscriptions", style = MaterialTheme.typography.titleMedium)

        // Dummy data for now. Real data comes from the database in Phase 3.
        SubscriptionCard(name = "Netflix", price = "₹499", progress = 0.8f, iconColor = Color(0xFFE50914), daysLeft = 2)
        SubscriptionCard(name = "Prime Video", price = "₹299", progress = 0.6f, iconColor = Color(0xFF00A8E1), daysLeft = 4)
        SubscriptionCard(name = "Spotify", price = "₹119", progress = 0.3f, iconColor = Color(0xFF1DB954))
        SubscriptionCard(name = "YouTube Premium", price = "₹149", progress = 0.1f, iconColor = Color(0xFFFF0000))
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ThemeCheckPreview() {
    SnipzTheme { ThemeCheck() }
}