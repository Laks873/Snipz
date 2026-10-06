package com.laks873.snipz.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = SnipzGreen,
    onPrimary = Color.White,
    primaryContainer = SnipzMint,
    onPrimaryContainer = SnipzGreenDark,
    secondary = SnipzGreenMedium,
    background = LightBackground,
    onBackground = LightTextPrimary,
    surface = LightBackground,
    onSurface = LightTextPrimary,
    surfaceVariant = LightCard,
    onSurfaceVariant = LightTextSecondary,
)

private val DarkColors = darkColorScheme(
    primary = SnipzGreenMedium,
    onPrimary = Color.White,
    primaryContainer = SnipzGreenDark,
    onPrimaryContainer = SnipzMint,
    secondary = SnipzSage,
    background = DarkBackground,
    onBackground = DarkTextPrimary,
    surface = DarkBackground,
    onSurface = DarkTextPrimary,
    surfaceVariant = DarkCard,
    onSurfaceVariant = DarkTextSecondary,
)

@Composable
fun SnipzTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}