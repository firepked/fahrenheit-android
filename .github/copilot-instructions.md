# Fahrenheit Android - Copilot Instructions

## Project Overview

Fahrenheit is an Audiobookshelf client for Android, with a focus on Fire Stick and other Android TV devices. The app provides a native Android interface for browsing libraries, playing audiobooks and podcasts, and syncing progress with an Audiobookshelf server.

**Key Points:**
- This is a client application only - no admin features
- Target platform: Fire Stick (Android TV) with support for other Android devices
- Uses Audiobookshelf API for all data operations

## Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose for TV (androidx.tv.material, androidx.compose)
- **API Client:** Retrofit with Gson for JSON serialization
- **Media Playback:** AndroidX Media (androidx.media, androidx.media2.session)
- **Image Loading:** Coil Compose
- **Security:** EncryptedSharedPreferences for token storage
- **Build System:** Gradle with Kotlin DSL
- **Minimum SDK:** 25 (Android 7.1)
- **Target SDK:** 34
- **Compile SDK:** 35
- **Java Version:** 17

## Project Structure

```
app/src/main/java/com/paulohenriquesg/fahrenheit/
├── main/               # Main activity and screen
├── ui/                 # UI components and theme
│   ├── theme/         # Theme, colors, typography
│   ├── components/    # Reusable UI components
│   ├── elements/      # UI elements (cards, images)
│   └── navigation/    # Navigation-related UI
├── api/               # API service and DTOs
├── storage/           # Data storage (SharedPreferences)
├── podcast/           # Podcast-related features
├── collection/        # Collection browsing
├── series/            # Series browsing
├── search/            # Search functionality
├── update/            # App update checks
└── utils/             # Utility classes
```

## Build and Test Commands

### Building
```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK (requires signing config)
./gradlew assembleRelease

# Clean build
./gradlew clean
```

### Testing
```bash
# Run unit tests
./gradlew test

# Run unit tests with coverage
./gradlew testDebugUnitTest

# Run Android instrumentation tests
./gradlew connectedAndroidTest
```

### Linting
```bash
# Run Kotlin linter
./gradlew lint

# Check for code issues
./gradlew check
```

### Running on Device
```bash
# Install debug build
./gradlew installDebug

# Install and run
./gradlew installDebug && adb shell am start -n com.paulohenriquesg.fahrenheit/.main.MainActivity
```

## Code Conventions

### DTO Consolidation Pattern

**Critical:** When working with API responses, follow the DTO consolidation pattern documented in `.claude.md`. Key principles:

1. **Extend existing DTOs** instead of creating new ones for data variations
2. **Use nullable fields** for fields that aren't present in all API response formats
3. **Add @SerializedName** for all API response format variations
4. **Create helper extensions** to abstract format differences
5. **Use Double for numeric types** (Gson auto-converts Float → Double)

Example:
```kotlin
// ✅ DO: Extend with nullable fields
data class Author(
    val id: String,
    val name: String,
    val numBooks: Int? = null  // Only in some responses
)

// ✅ DO: Add helper extensions
fun ItemMetadata.getAuthorsDisplay(): String =
    authors?.joinToString(", ") { it.name } ?: authorName.orEmpty()
```

### Kotlin Style

- Follow standard Kotlin conventions
- Use data classes for DTOs and simple models
- Prefer immutability (val over var)
- Use nullable types appropriately
- Leverage Kotlin extensions for cleaner code

### Compose UI

- Use `@Composable` functions for UI components
- Follow Material Design 3 for TV guidelines
- Keep composables focused and reusable
- Use remember for state management
- Prefer stateless composables when possible

### API Integration

- All API calls through `ApiService` interface
- Use Retrofit with Gson converter
- Enable HTTP logging in debug builds via `HttpLoggingInterceptor.Level.BODY`
- Handle errors gracefully with proper user feedback

## Git Workflow

### Commit Messages

**ALWAYS use one-line commit messages without attribution.**

- Keep messages concise and descriptive
- No multi-line explanations
- No "Co-Authored-By" or attribution lines
- No emoji or special formatting

Examples:
```
Consolidate Author DTOs to support search and detailed responses
Improve dark mode button visibility and add purple styling for disabled buttons
Fix crash when opening book details from home screen
```

### Branch Naming

- Feature branches: `feature/description`
- Bug fixes: `fix/description`
- Refactoring: `refactor/description`

## Security Considerations

- **Never commit secrets** - Use environment variables for sensitive data
- **Use EncryptedSharedPreferences** for storing tokens and credentials
- **Validate user input** before sending to API
- **Handle API errors** without exposing sensitive information

## Debugging

### Viewing API Responses

The app uses `HttpLoggingInterceptor.Level.BODY` in `ApiClient.kt` for detailed API logging.

```bash
# Clear logs and capture new ones
adb logcat -c

# View OkHttp logs (API requests/responses)
adb logcat -d | grep -A 50 "OkHttpClient\|okhttp3"

# View custom logs
adb logcat -d | grep -E "ShelfDeserializer|MainScreen"
```

### Adding Debug Logging

Use Android's Log class:
```kotlin
import android.util.Log

Log.d("TagName", "Debug message")
Log.e("TagName", "Error message", throwable)
```

## Testing Guidelines

- Unit tests go in `app/src/test/`
- Android instrumentation tests go in `app/src/androidTest/`
- Use JUnit for test framework
- Use Mockito for mocking dependencies
- Test DTO deserialization from all API response formats

Example:
```kotlin
@Test
fun `DTO handles minified response`() {
    val json = """{"authorName": "John Doe"}"""
    val metadata = Gson().fromJson(json, ItemMetadata::class.java)
    assertEquals("John Doe", metadata.getAuthorsDisplay())
}
```

## Common Pitfalls

1. **Don't create duplicate DTOs** - Extend existing ones with nullable fields
2. **Don't forget @SerializedName** - Needed for Gson deserialization
3. **Don't ignore API logging** - Check logs when debugging API issues
4. **Don't skip testing** - Always test DTO deserialization from multiple formats
5. **Don't remove working tests** - Only modify tests when fixing bugs or adding features

## Dependencies

When adding new dependencies:
- Check if similar functionality exists in current dependencies
- Prefer AndroidX libraries for Android features
- Keep dependencies up to date but stable
- Test thoroughly after adding new dependencies

## Resources

- **Audiobookshelf API**: https://api.audiobookshelf.org/ - Official API documentation
- **Audiobookshelf API GitHub**: https://github.com/audiobookshelf/audiobookshelf-api-docs
- **Android TV Guidelines**: Follow Material Design for TV best practices
- **Jetpack Compose**: Use modern Compose patterns for UI development
