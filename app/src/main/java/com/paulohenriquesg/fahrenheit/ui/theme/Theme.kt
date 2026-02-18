package com.paulohenriquesg.fahrenheit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme
import androidx.tv.material3.lightColorScheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun FahrenheitTheme(
    isInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    // Observe theme state from ThemeManager
    val isDarkTheme by ThemeManager.isDarkTheme

    val colorScheme = if (isDarkTheme) {
        darkColorScheme(
            primary = SpotifyGreen,                        // Accent: Spotify green
            secondary = SpotifyTextGray,                   // Muted secondary text
            tertiary = SpotifyGreenLight,                  // Tertiary accent variant
            background = SpotifyBlack,                     // Deep dark background
            surface = SpotifyDarkGray,                     // Card / elevated surface
            surfaceVariant = FocusedItemBackground,        // Focused card background
            onBackground = Color(0xFFFFFFFF),              // White text on background
            onSurface = Color(0xFFFFFFFF),                 // White text on cards
            onSurfaceVariant = SpotifyTextGray,            // Muted text for secondary info
            onPrimary = Color(0xFF000000),                 // Black text on green buttons
            onSecondary = Color(0xFFFFFFFF),               // White for secondary button text
            onSecondaryContainer = Color(0xFFFFFFFF),      // White for container text
            onTertiary = Color(0xFF000000),                // Black on tertiary accent
            onTertiaryContainer = Color(0xFFFFFFFF),       // White for tertiary containers
            primaryContainer = SpotifyMediumGray,          // Container behind primary actions
            secondaryContainer = SpotifyMediumGray,        // Medium gray container
            tertiaryContainer = SpotifyLightGray           // Light gray container
        )
    } else {
        lightColorScheme(
            primary = SpotifyGreenDark,                    // Accent: darker green for light bg
            secondary = Color(0xFF535353),                  // Muted secondary
            tertiary = SpotifyGreen,                       // Tertiary accent
            background = Color(0xFFF8F8F8),                // Off-white background
            surface = Color(0xFFFFFFFF),                   // White cards
            surfaceVariant = Color(0xFFE8F5E9),            // Light green tint for focus
            onBackground = Color(0xFF191414),              // Near-black text
            onSurface = Color(0xFF191414),                 // Near-black text on cards
            onSurfaceVariant = Color(0xFF535353),           // Muted text
            onPrimary = Color(0xFFFFFFFF),                 // White on green buttons
            primaryContainer = Color(0xFFE8F5E9),          // Light green container
            secondaryContainer = Color(0xFFE0E0E0),        // Light gray container
            tertiaryContainer = Color(0xFFE8F5E9)          // Light green container
        )
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}