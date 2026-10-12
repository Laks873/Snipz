package com.laks873.snipz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun OfferCard(
    name: String,
    price: String,
    backgroundColor: Color,        // the service's brand colour
    modifier: Modifier = Modifier,
    billingPeriod: String = "month",
    badge: String = name.take(2),  // "Ps", "Ca"... a stand-in for the logo
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .size(width = 130.dp, height = 140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween  // name at top, logo at bottom
    ) {
        Column {
            Text(name, color = Color.White, style = MaterialTheme.typography.bodyMedium)
            Text(
                "$price/$billingPeriod",
                color = Color.White.copy(alpha = 0.8f),
                style = MaterialTheme.typography.labelSmall
            )
        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                badge,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}