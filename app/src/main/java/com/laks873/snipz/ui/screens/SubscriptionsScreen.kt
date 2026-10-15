package com.laks873.snipz.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laks873.snipz.R
import com.laks873.snipz.ui.components.SnipzChip
import com.laks873.snipz.ui.components.SubscriptionCard
import com.laks873.snipz.ui.theme.SnipzTheme
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun SubscriptionsScreen(
    subscriptions: List<SubscriptionUi>,
    monthlySpend: List<MonthlySpend>,
    modifier: Modifier = Modifier
) {
    // Two pieces of state: which filter is on, and which month is showing
    var showActive by remember { mutableStateOf(true) }
    var monthIndex by remember { mutableStateOf(monthlySpend.lastIndex) } // start at latest month

    val visibleSubscriptions = subscriptions.filter { it.isActive == showActive }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { Text("My subscriptions", style = MaterialTheme.typography.titleLarge) }
        item { Text("Total monthly spend", style = MaterialTheme.typography.labelLarge) }

        item {
            MonthlySpendCard(
                current = monthlySpend[monthIndex],
                previous = monthlySpend.getOrNull(monthIndex - 1),
                canGoBack = monthIndex > 0,
                canGoForward = monthIndex < monthlySpend.lastIndex,
                onBack = { monthIndex-- },
                onForward = { monthIndex++ }
            )
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SnipzChip("Active", selected = showActive, onClick = { showActive = true })
                SnipzChip("Cancelled", selected = !showActive, onClick = { showActive = false })
            }
        }

        items(visibleSubscriptions) { sub ->
            SubscriptionCard(
                name = sub.name,
                price = sub.price,
                progress = sub.progress,
                iconColor = sub.iconColor,
                daysLeft = sub.daysLeft,
                showProgress = sub.isActive
            )
        }
    }
}

@Composable
private fun MonthlySpendCard(
    current: MonthlySpend,
    previous: MonthlySpend?,
    canGoBack: Boolean,
    canGoForward: Boolean,
    onBack: () -> Unit,
    onForward: () -> Unit
) {
    // Soft mint-to-grey gradient that adapts to light and dark mode
    val gradient = Brush.linearGradient(
        listOf(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.surfaceVariant)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(gradient)
            .padding(vertical = 16.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack, enabled = canGoBack) {
            Icon(painterResource(R.drawable.ic_chevron_left), contentDescription = "Previous month")
        }
        Column(Modifier.weight(1f)) {
            Text(current.month, style = MaterialTheme.typography.bodyMedium)
            Text("₹%,d".format(current.amount), style = MaterialTheme.typography.headlineMedium)
        }
        if (previous != null) {
            ChangeBadge(current = current.amount, previous = previous.amount)
        }
        IconButton(onClick = onForward, enabled = canGoForward) {
            Icon(painterResource(R.drawable.ic_chevron_right), contentDescription = "Next month")
        }
    }
}

// The "2% ↑" badge: change compared with the previous month
@Composable
private fun ChangeBadge(current: Int, previous: Int) {
    val change = (current - previous) * 100f / previous
    val arrow = if (change >= 0) "↑" else "↓"
    Text(
        text = "${abs(change).roundToInt()}% $arrow",
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SubscriptionsScreenPreview() {
    SnipzTheme {
        SubscriptionsScreen(subscriptions = sampleSubscriptions, monthlySpend = sampleMonthlySpend)
    }
}

