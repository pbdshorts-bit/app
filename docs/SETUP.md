# Map Your Move Setup Guide

## Prerequisites

- Android Studio (Giraffe or later)
- JDK 11 or higher
- Android SDK API 28 or higher
- Firebase Account
- Google Cloud Project
- Git

## Step 1: Clone Repository

```bash
git clone https://github.com/pbdshorts-bit/app.git
cd app
```

## Step 2: Firebase Setup

1. Go to [Firebase Console](https://console.firebase.google.com)
2. Create a new project "Map Your Move"
3. Create an Android app in the project
4. Download `google-services.json`
5. Place it in `android/app/`

### Enable Firebase Services

**Authentication:**
- Enable Email/Password authentication
- Enable Google Sign-In
- Enable Apple Sign-In

**Firestore Database:**
- Create Firestore database
- Set security rules:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /users/{userId} {
      allow read, write: if request.auth.uid == userId;
    }
    match /itineraries/{doc=**} {
      allow read, write: if request.auth != null;
    }
    match /bookings/{doc=**} {
      allow read, write: if request.auth != null;
    }
  }
}
```

**Cloud Storage:**
- Create storage bucket
- Set security rules:

```javascript
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    match /uploads/{userId}/{allPaths=**} {
      allow read, write: if request.auth.uid == userId;
    }
  }
}
```

## Step 3: Google Maps API

1. Enable Google Maps API in Google Cloud Console
2. Create API key
3. Add to `local.properties`:

```
GOOGLE_MAPS_API_KEY=YOUR_KEY_HERE
```

## Step 4: Weather API

1. Sign up at [OpenWeatherMap](https://openweathermap.org/api)
2. Get API key
3. Add to `local.properties`:

```
OPENWEATHER_API_KEY=YOUR_KEY_HERE
```

## Step 5: Build & Run

```bash
# Open in Android Studio
open -a "Android Studio" .

# Or build from command line
./gradlew build

# Install on emulator/device
./gradlew installDebug
```

## Step 6: Generate APK

### Debug APK
```bash
./gradlew assembleDebug
# APK: android/app/build/outputs/apk/debug/app-debug.apk
```

### Release APK

1. Create keystore:
```bash
keytool -genkey -v -keystore map-your-move.keystore \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias map-your-move
```

2. Build signed APK:
```bash
./gradlew assembleRelease
# APK: android/app/build/outputs/apk/release/app-release.apk
```

3. Install on device:
```bash
adb install -r app-release.apk
```

## Troubleshooting

### Gradle Sync Issues
- File → Invalidate Caches → Restart
- Delete `.gradle` and `build` folders
- Sync again

### Firebase Connection Issues
- Verify `google-services.json` is in correct location
- Check internet connection
- Verify Firebase credentials

### Build Errors
- Ensure JDK 11+ is installed
- Check Android SDK versions match `build.gradle`
- Clear build cache: `./gradlew clean`

## Environment Variables

Create `local.properties` in root:

```properties
sdk.dir=/path/to/android/sdk
GOOGLE_MAPS_API_KEY=your_key
OPENWEATHER_API_KEY=your_key
```

## Next Steps

1. Review [Architecture Guide](./ARCHITECTURE.md)
2. Check [API Documentation](./API.md)
3. Start with LoginScreen implementation
4. Follow code structure patterns

## Support

For issues:
1. Check existing issues
2. Create detailed bug report
3. Include logs and screenshots

---

Happy developing! 🚀
