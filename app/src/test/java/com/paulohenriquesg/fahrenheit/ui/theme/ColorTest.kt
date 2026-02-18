package com.paulohenriquesg.fahrenheit.ui.theme

import org.junit.Assert.*
import org.junit.Test

/**
 * Unit tests for the Spotify-inspired color palette.
 * Validates that accent colors, surface tones, and focus colors are defined correctly.
 */
class ColorTest {

    @Test
    fun `SpotifyGreen accent should be the Spotify brand green`() {
        assertEquals(0xFF1DB954L, SpotifyGreen.value.toLong())
    }

    @Test
    fun `SpotifyGreenDark should be darker than SpotifyGreen`() {
        // SpotifyGreenDark (0xFF1AA34A) should have a lower green channel than SpotifyGreen (0xFF1DB954)
        val greenChannelDark = (SpotifyGreenDark.value shr 8) and 0xFF.toULong()
        val greenChannelLight = (SpotifyGreen.value shr 8) and 0xFF.toULong()
        assertTrue(
            "SpotifyGreenDark green channel ($greenChannelDark) should be <= SpotifyGreen ($greenChannelLight)",
            greenChannelDark <= greenChannelLight
        )
    }

    @Test
    fun `SpotifyGreenLight should be lighter than SpotifyGreen`() {
        val greenChannelBright = (SpotifyGreenLight.value shr 8) and 0xFF.toULong()
        val greenChannelBase = (SpotifyGreen.value shr 8) and 0xFF.toULong()
        assertTrue(
            "SpotifyGreenLight green channel ($greenChannelBright) should be >= SpotifyGreen ($greenChannelBase)",
            greenChannelBright >= greenChannelBase
        )
    }

    @Test
    fun `dark surface colors should progressively lighten`() {
        // SpotifyBlack < SpotifyDarkGray < SpotifyMediumGray < SpotifyLightGray
        assertTrue(SpotifyBlack.value < SpotifyDarkGray.value)
        assertTrue(SpotifyDarkGray.value < SpotifyMediumGray.value)
        assertTrue(SpotifyMediumGray.value < SpotifyLightGray.value)
    }

    @Test
    fun `FocusRingColor should match SpotifyGreenLight`() {
        assertEquals(SpotifyGreenLight.value, FocusRingColor.value)
    }

    @Test
    fun `SelectedItemBackground should be darker than FocusedItemBackground`() {
        assertTrue(
            "SelectedItemBackground should be darker than FocusedItemBackground",
            SelectedItemBackground.value < FocusedItemBackground.value
        )
    }

    @Test
    fun `original purple and pink colors retain their values`() {
        // Ensure backward compatibility — original colors retain their original ARGB values
        assertEquals(0xFFD0BCFFL, Purple80.value.toLong())
        assertEquals(0xFFCCC2DCL, PurpleGrey80.value.toLong())
        assertEquals(0xFFEFB8C8L, Pink80.value.toLong())
        assertEquals(0xFF6650A4L, Purple40.value.toLong())
        assertEquals(0xFF625B71L, PurpleGrey40.value.toLong())
        assertEquals(0xFF7D5260L, Pink40.value.toLong())
    }

    @Test
    fun `SpotifyTextGray should have high luminance for readability`() {
        // SpotifyTextGray (0xFFB3B3B3) — the red channel as a proxy for luminance
        val redChannel = (SpotifyTextGray.value shr 16) and 0xFF.toULong()
        assertTrue(
            "SpotifyTextGray red channel ($redChannel) should be >= 160 for readability",
            redChannel >= 160uL
        )
    }
}
