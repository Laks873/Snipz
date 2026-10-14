package com.laks873.snipz.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laks873.snipz.R
import com.laks873.snipz.ui.components.OfferCard
import com.laks873.snipz.ui.components.SubscriptionCard
import com.laks873.snipz.ui.theme.SnipzTheme

// ---------- Temporary data models (replaced by the real database in Phase 3) ----------

data class SubscriptionUi(
    val name: String,
    val price: String,
    val progress: Float,
    val iconColor: Color,
    val daysLeft: Int? = null
)

data class OfferUi(
    val name: String,
    val price: String,
    val backgroundColor: Color,
    val badge: String
)

val sampleOffers = listOf(
    OfferUi("Photoshop", "₹1,676", Color(0xFF0A1F44), "Ps"),
    OfferUi("JioHotstar", "₹149", Color(0xFF0F1014), "JH"),
    OfferUi("Canva Pro", "₹500", Color(0xFF5B2BC9), "Ca")
)

val sampleSubscriptions = listOf(
    SubscriptionUi("Netflix", "₹499", 0.8f, Color(0xFFE50914), daysLeft = 2),
    SubscriptionUi("Prime Video", "₹299", 0.6f, Color(0xFF00A8E1), daysLeft = 4),
    SubscriptionUi("Spotify", "₹119", 0.3f, Color(0xFF1DB954)),
    SubscriptionUi("YouTube Premium", "₹149", 0.1f, Color(0xFFFF0000)),
    SubscriptionUi("Figma", "₹1,250", 0.5f, Color(0xFF1E1E1E)),
    SubscriptionUi("Apple Music", "₹99", 0.45f, Color(0xFFFA243C))
)

// ---------- The screen ----------

@Composable
fun HomeScreen(
    userName: String,
    offers: List<OfferUi>,
    subscriptions: List<SubscriptionUi>,
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onAddClick: () -> Unit = {}
) {
    // Box lets us layer the + button on top of the scrolling list
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            // Extra bottom padding so the last card isn't hidden behind the + button
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 88.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { HomeHeader(userName, onMenuClick, onProfileClick) }

            item { SectionTitle("New offers") }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(offers) { offer ->
                        OfferCard(
                            name = offer.name,
                            price = offer.price,
                            backgroundColor = offer.backgroundColor,
                            badge = offer.badge
                        )
                    }
                }
            }

            item { SectionTitle("My Subscriptions") }
            items(subscriptions) { sub ->
                SubscriptionCard(
                    name = sub.name,
                    price = sub.price,
                    progress = sub.progress,
                    iconColor = sub.iconColor,
                    daysLeft = sub.daysLeft
                )
            }
        }

        SmallFloatingActionButton(
            onClick = onAddClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(painterResource(R.drawable.ic_add), contentDescription = "Add subscription")
        }
    }
}

@Composable
private fun HomeHeader(
    userName: String,
    onMenuClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onMenuClick) {
            Icon(painterResource(R.drawable.ic_menu), contentDescription = "Menu")
        }
        Spacer(Modifier.weight(1f))
        Text("Hi! $userName", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.width(8.dp))
        // Placeholder avatar: initial in a mint circle. Real avatars come with onboarding.
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .clickable(onClick = onProfileClick),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = userName.take(1).uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.labelLarge)
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    SnipzTheme {
        HomeScreen(userName = "Lakshya", offers = sampleOffers, subscriptions = sampleSubscriptions)
    }
}