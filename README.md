# Map Your Move - Travel Itinerary & Booking Management App

**Map Your Move** is a feature-rich mobile application designed to enhance your travel experience by providing intelligent itinerary planning, booking management, and offline-first capabilities.

## 📱 About the App

Map Your Move is an Android application that helps travelers:
- Create personalized itineraries for any destination
- Upload and manage travel documents (flight, hotel, train tickets)
- View location ratings and recommendations
- Plan trips based on weather conditions and traveler preferences
- Access maps with OS-specific integration (Google Maps/Apple Maps)
- Work seamlessly offline
- Choose from multiple languages during signup

### Key Features

✨ **Core Functionality**
- 🗺️ **Smart Itinerary Builder** - Create customized travel plans for any destination
- 🎫 **Ticket Management** - Upload, organize, and manage flight, hotel, and train bookings
- ⭐ **Ratings & Reviews** - View community ratings for hotels, restaurants, attractions
- 🏨 **Hotel Recommendations** - AI-powered hotel suggestions based on ratings and preferences
- 🌤️ **Weather Integration** - Plan activities based on weather forecasts
- 📍 **Location Intelligence** - Discover trending destinations and popular sites by country
- 🔐 **Secure Authentication** - Google, Apple, or Email login with encrypted data storage
- 🌍 **Multi-Language Support** - French, Hindi, Spanish, Japanese, Korean, Italian, Turkish
- 📱 **Offline Mode** - Full functionality without internet connection
- 🎨 **Interactive UI** - Beautiful, intuitive, exquisite interface design
- 🔗 **External Links** - Direct links to Google Maps, Apple Maps, booking platforms

## 📋 Project Structure

```
app/
├── android/                          # Android native code
│   ├── app/
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/             # Kotlin/Java source files
│   │   │   │   ├── res/              # Resources (layouts, drawables, strings)
│   │   │   │   └── AndroidManifest.xml
│   │   │   ├── test/                 # Unit tests
│   │   │   └── androidTest/          # Instrumentation tests
│   │   ├── build.gradle              # App-level dependencies
│   │   └── proguard-rules.pro        # Obfuscation rules
│   └── build.gradle                  # Project-level configuration
├── assets/                           # App assets (icons, images, etc.)
├── docs/                             # Documentation
├── tests/                            # Test suites
├── build.gradle                      # Root Gradle configuration
├── settings.gradle                   # Gradle settings
├── gradle.properties                 # Gradle properties
└── README.md                         # This file
```

## 🛠️ Technology Stack

### Frontend
- **Kotlin** - Modern Android development language
- **Jetpack Compose** - Modern declarative UI toolkit
- **Material Design 3** - Beautiful and interactive UI components

### Backend & Data
- **Firebase Authentication** - Google, Apple, and Email authentication
- **Firestore** - Cloud database with offline sync capability
- **Firebase Storage** - Secure file storage for tickets and documents
- **Room Database** - Local offline-first data persistence
- **Retrofit** - REST API client for weather and location APIs

### Libraries & Tools
- **Coroutines** - Async programming and lifecycle management
- **Hilt** - Dependency injection
- **Navigation Component** - In-app navigation
- **Data Store** - User preferences and settings storage
- **Glide** - Image loading and caching
- **Google Maps SDK** - Map integration
- **Apple Maps** - iOS map integration (via deep links)

## 🚀 Getting Started

### Prerequisites
- Android Studio (latest version)
- JDK 11 or higher
- Android SDK (API 28+)
- Google/Apple developer accounts (for authentication)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/pbdshorts-bit/app.git
   cd app
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory

3. **Configure Firebase**
   - Create a Firebase project at [Firebase Console](https://console.firebase.google.com)
   - Download `google-services.json`
   - Place it in `android/app/`
   - Enable Authentication (Google, Apple, Email)
   - Enable Firestore Database
   - Enable Storage

4. **Build & Run**
   ```bash
   ./gradlew build
   ./gradlew installDebug  # For emulator/device
   ```

5. **Generate APK**
   ```bash
   ./gradlew assembleRelease
   # APK will be at: android/app/build/outputs/apk/release/app-release.apk
   ```

## 📊 Architecture

The app follows **MVVM (Model-View-ViewModel)** architecture with clean code principles:

```
Presentation Layer
    ├── UI (Composables)
    ├── ViewModels
    └── State Management

Domain Layer
    ├── Use Cases
    ├── Repositories (Interfaces)
    └── Models

Data Layer
    ├── API Services
    ├── Database (Room)
    ├── Preferences (DataStore)
    ├── Firebase
    └── Repositories (Implementation)
```

## 🔐 Security Features

- **End-to-End Encryption** for stored documents
- **OAuth 2.0** for secure authentication
- **API Key Management** for external services
- **Local Encryption** for sensitive user data
- **Secure Data Deletion** on logout

## 🌐 API Integrations

- **Google Maps API** - Location services and mapping
- **OpenWeatherMap API** - Weather forecasts
- **Google Places API** - Venue information and ratings
- **Booking.com API** - Hotel recommendations
- **Skyscanner API** - Flight information

## 📦 APK Distribution

### Building Release APK
```bash
# Ensure you have a keystore
keytool -genkey -v -keystore map-your-move.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias map-your-move

# Build signed APK
./gradlew assembleRelease

# Generated APK location
# android/app/build/outputs/apk/release/app-release.apk
```

### Installation on Device
```bash
adb install -r app-release.apk
```

## 🗣️ Language Support

The app supports the following languages:
- 🇬🇧 English
- 🇫🇷 French (Français)
- 🇮🇳 Hindi (हिंदी)
- 🇪🇸 Spanish (Español)
- 🇯🇵 Japanese (日本語)
- 🇰🇷 Korean (한국어)
- 🇮🇹 Italian (Italiano)
- 🇹🇷 Turkish (Türkçe)

Users select their preferred language during the signup process.

## 🎨 UI/UX Design

Map Your Move features:
- **Interactive Components** - Smooth animations and transitions
- **Material Design 3** - Modern design language
- **Dark Mode Support** - Comfortable viewing in any lighting
- **Responsive Layout** - Adapts to all device sizes
- **Accessibility** - Full support for screen readers and accessibility services

## 📚 Documentation

- [Architecture Guide](./docs/ARCHITECTURE.md)
- [API Documentation](./docs/API.md)
- [Setup Guide](./docs/SETUP.md)
- [Contributing Guidelines](./CONTRIBUTING.md)

## 🧪 Testing

### Unit Tests
```bash
./gradlew test
```

### Instrumentation Tests
```bash
./gradlew connectedAndroidTest
```

### Coverage Report
```bash
./gradlew testDebugUnitTest --tests com.mapyourmove.*
```

## 🤝 Contributing

Contributions are welcome! Please see [CONTRIBUTING.md](./CONTRIBUTING.md) for guidelines.

## 📝 License

This project is licensed under the MIT License - see [LICENSE](./LICENSE) file for details.

## 🆘 Support

For issues, questions, or suggestions, please:
1. Check [GitHub Issues](https://github.com/pbdshorts-bit/app/issues)
2. Create a new issue with detailed information
3. Join discussions at [GitHub Discussions](https://github.com/pbdshorts-bit/app/discussions)

## 🗺️ Roadmap

### Phase 1 (Current)
- [x] Project setup and structure
- [ ] Authentication system
- [ ] Core itinerary builder
- [ ] Offline database
- [ ] Multi-language support

### Phase 2
- [ ] Ticket upload and management
- [ ] Hotel recommendations engine
- [ ] Weather integration
- [ ] Ratings system

### Phase 3
- [ ] Maps integration (Google/Apple)
- [ ] Trending destinations
- [ ] Social features
- [ ] Advanced analytics

### Phase 4
- [ ] AI-powered suggestions
- [ ] Real-time collaboration
- [ ] Premium features

## 📞 Contact

- **Developer**: pbdshorts-bit
- **Project**: Map Your Move
- **Email**: [Contact via GitHub Issues]
- **Website**: [Coming Soon]

---

**Happy travels with Map Your Move!** ✈️🏨🗽
