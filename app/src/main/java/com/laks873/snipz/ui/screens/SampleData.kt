package com.laks873.snipz.ui.screens

import androidx.compose.ui.graphics.Color

// Temporary data models and dummy data. Replaced by the real database in Phase 3.

data class SubscriptionUi(
    val name: String,
    val price: String,
    val progress: Float,
    val iconColor: Color,
    val daysLeft: Int? = null,
    val isActive: Boolean = true
)

data class OfferUi(
    val name: String,
    val price: String,
    val backgroundColor: Color,
    val badge: String
)

data class MonthlySpend(val month: String, val amount: Int)

val sampleOffers = listOf(
    OfferUi("Photoshop", "₹1,676", Color(0xFF0A1F44), "Ps"),
    OfferUi("JioHotstar", "₹149", Color(0xFF0F1014), "JH"),
    OfferUi("Canva Pro", "₹500", Color(0xFF5B2BC9), "Ca")
)

val sampleSubscriptions = listOf(
    // Active
    SubscriptionUi("Netflix", "₹499", 0.8f, Color(0xFFE50914), daysLeft = 2),
    SubscriptionUi("Prime Video", "₹299", 0.6f, Color(0xFF00A8E1), daysLeft = 4),
    SubscriptionUi("Spotify", "₹119", 0.3f, Color(0xFF1DB954)),
    SubscriptionUi("YouTube Premium", "₹149", 0.1f, Color(0xFFFF0000)),
    SubscriptionUi("Figma", "₹1,250", 0.5f, Color(0xFF1E1E1E)),
    SubscriptionUi("Apple Music", "₹99", 0.45f, Color(0xFFFA243C)),
    // Cancelled
    SubscriptionUi("Medium", "₹450", 0f, Color(0xFF000000), isActive = false),
    SubscriptionUi("SoundCloud", "₹139", 0f, Color(0xFFFF5500), isActive = false),
    SubscriptionUi("Twitch", "₹399", 0f, Color(0xFF9146FF), isActive = false)
)

val sampleMonthlySpend = listOf(
    MonthlySpend("May", 1980),
    MonthlySpend("June", 2120),
    MonthlySpend("July", 2240),
    MonthlySpend("August", 2240),
    MonthlySpend("September", 2365),
    MonthlySpend("October", 2415)
)

