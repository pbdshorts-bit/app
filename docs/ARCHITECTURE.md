# Map Your Move - Architecture Guide

## Overview

Map Your Move follows the **MVVM (Model-View-ViewModel)** architecture pattern combined with **Clean Architecture** principles for maintainability, scalability, and testability.

## Architecture Layers

### 1. **Presentation Layer**
The UI layer responsible for displaying data and handling user interactions.

```
Presentation Layer
├── UI (Jetpack Compose)
│   ├── Screens
│   ├── Components
│   └── Navigation
├── ViewModels
└── State Management
```

**Key Components:**
- **Composables**: Reusable UI components built with Jetpack Compose
- **Screens**: Full-screen UI for each app section
- **ViewModels**: Manage UI state and business logic
- **Navigation**: Handle app navigation flows

**Example Structure:**
```
presentation/
├── screens/
│   ├── auth/
│   │   ├── LoginScreen.kt
│   │   ├── SignupScreen.kt
│   │   └── LanguageSelectionScreen.kt
│   ├── itinerary/
│   │   ├── ItineraryListScreen.kt
│   │   ├── ItineraryDetailScreen.kt
│   │   └── CreateItineraryScreen.kt
│   ├── booking/
│   │   ├── BookingUploadScreen.kt
│   │   └── BookingDetailScreen.kt
│   └── discover/
│       ├── DiscoverScreen.kt
│       ├── HotelRecommendationScreen.kt
│       └── LocationDetailScreen.kt
├── viewmodels/
│   ├── AuthViewModel.kt
│   ├── ItineraryViewModel.kt
│   ├── BookingViewModel.kt
│   └── DiscoverViewModel.kt
├── components/
│   ├── BottomNavBar.kt
│   ├── ItineraryCard.kt
│   ├── HotelCard.kt
│   └── RatingCard.kt
├── navigation/
│   ├── NavGraph.kt
│   └── Routes.kt
└── theme/
    ├── Color.kt
    ├── Typography.kt
    └── Theme.kt
```

### 2. **Domain Layer**
Business logic layer that is independent of frameworks.

```
Domain Layer
├── Use Cases
├── Repositories (Interfaces)
└── Models (Entities)
```

**Key Components:**
- **Use Cases**: Encapsulate single business logic operations
- **Repositories**: Define data access contracts
- **Models**: Business entities and domain objects

**Example Structure:**
```
domain/
├── usecases/
│   ├── auth/
│   │   ├── LoginUseCase.kt
│   │   ├── SignupUseCase.kt
│   │   └── LogoutUseCase.kt
│   ├── itinerary/
│   │   ├── CreateItineraryUseCase.kt
│   │   ├── GetItinerariesUseCase.kt
│   │   └── UpdateItineraryUseCase.kt
│   ├── booking/
│   │   ├── UploadTicketUseCase.kt
│   │   └── GetBookingsUseCase.kt
│   └── discover/
│       ├── GetHotelRecommendationsUseCase.kt
│       ├── GetLocationsUseCase.kt
│       └── GetWeatherUseCase.kt
├── repositories/
│   ├── AuthRepository.kt
│   ├── ItineraryRepository.kt
│   ├── BookingRepository.kt
│   └── DiscoverRepository.kt
└── models/
    ├── User.kt
    ├── Itinerary.kt
    ├── Booking.kt
    ├── Hotel.kt
    ├── Location.kt
    └── Weather.kt
```

### 3. **Data Layer**
Implements data access logic and manages data sources.

```
Data Layer
├── API Services
├── Database (Room)
├── Preferences (DataStore)
├── Firebase
└── Repositories (Implementation)
```

**Key Components:**
- **API Services**: Retrofit services for remote data
- **Database**: Room entities and DAOs for local storage
- **DataStore**: User preferences and settings
- **Repositories**: Implement domain repository interfaces
- **Data Models**: API and database entities

**Example Structure:**
```
data/
├── api/
│   ├── services/
│   │   ├── WeatherApiService.kt
│   │   ├── LocationApiService.kt
│   │   └── BookingApiService.kt
│   ├── interceptors/
│   │   ├── AuthInterceptor.kt
│   │   └── ErrorHandlingInterceptor.kt
│   └── response/
│       ├── WeatherResponse.kt
│       └── LocationResponse.kt
├── database/
│   ├── AppDatabase.kt
│   ├── entities/
│   │   ├── ItineraryEntity.kt
│   │   ├── BookingEntity.kt
│   │   └── LocationEntity.kt
│   └── daos/
│       ├── ItineraryDao.kt
│       ├── BookingDao.kt
│       └── LocationDao.kt
├── preferences/
│   ├── UserPreferences.kt
│   └── AppSettings.kt
├── firebase/
│   ├── FirebaseAuthDataSource.kt
│   ├── FirebaseFirestoreDataSource.kt
│   └── FirebaseStorageDataSource.kt
└── repositories/
    ├── AuthRepositoryImpl.kt
    ├── ItineraryRepositoryImpl.kt
    ├── BookingRepositoryImpl.kt
    └── DiscoverRepositoryImpl.kt
```

## Data Flow

### Authentication Flow
```
User Input (UI)
    ↓
LoginViewModel.login()
    ↓
LoginUseCase
    ↓
AuthRepository.login()
    ↓
FirebaseAuthDataSource.authenticateWithEmail()
    ↓
Firebase Authentication
    ↓
Store Token in DataStore
    ↓
Update ViewModel State
    ↓
Navigate to Home
```

### Offline-First Data Sync
```
Network Request
    ↓
Try Remote Data (Firebase/API)
    ↓
Cache in Room Database
    ↓
Return Cached Data if Offline
    ↓
Sync when Online Again
```

## Dependency Injection (Hilt)

Hilt is used for dependency injection, promoting loose coupling and testability.

**Example Module:**
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    
    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        dataStore: DataStore<Preferences>
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth, dataStore)
    }
}
```

## State Management

### ViewModel State Pattern
```kotlin
data class ItineraryUIState(
    val isLoading: Boolean = false,
    val itineraries: List<Itinerary> = emptyList(),
    val error: String? = null,
    val selectedItinerary: Itinerary? = null
)

class ItineraryViewModel(
    private val getItinerariesUseCase: GetItinerariesUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ItineraryUIState())
    val uiState: StateFlow<ItineraryUIState> = _uiState.asStateFlow()
    
    fun loadItineraries() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val itineraries = getItinerariesUseCase()
                _uiState.value = _uiState.value.copy(
                    itineraries = itineraries,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }
}
```

## Network Strategy

### Retrofit Configuration
- Base URL from BuildConfig
- OAuth 2.0 interceptor for authentication
- Error handling and retry logic
- Request/Response logging in debug mode

### Firebase Integration
- Real-time database synchronization
- Offline persistence enabled
- Transaction support for consistency
- Security rules enforcement

## Local Database Strategy

### Room Database
- Entities for all major models
- DAOs for CRUD operations
- Migrations for schema updates
- Query optimization with indexes

### DataStore for Preferences
- User settings and preferences
- Authentication tokens
- App configuration
- Language and theme preferences

## Error Handling

### Structured Exception Handling
```kotlin
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

// Usage in repository
suspend fun getItineraries(): Result<List<Itinerary>> = withContext(Dispatchers.IO) {
    return@withContext try {
        val data = api.getItineraries()
        Result.Success(data.toDomainModels())
    } catch (e: Exception) {
        Result.Error(e)
    }
}
```

## Testing Strategy

### Unit Tests
- Test use cases with mocked repositories
- Test ViewModels with mocked use cases
- Repository tests with mock data sources

### Integration Tests
- Test Room database operations
- Test Firebase interactions
- Test API calls with mock responses

### UI Tests
- Espresso tests for critical flows
- Compose UI tests for components
- Navigation tests

## Performance Optimization

1. **Image Loading**: Glide/Coil with caching and resizing
2. **Database Queries**: Indexed queries and pagination
3. **Network Requests**: Request caching, batching
4. **Compose Performance**: Remember blocks, LazyLists
5. **Memory Management**: Proper coroutine cancellation

## Security

1. **Data Encryption**: Room encrypted database
2. **Network Security**: HTTPS/TLS, certificate pinning
3. **Local Storage**: Encrypted shared preferences
4. **API Keys**: Secured in BuildConfig
5. **Authentication**: OAuth 2.0, token refresh

## Module Organization

```
com.mapyourmove.app
├── data/
│   ├── api/
│   ├── database/
│   ├── preferences/
│   ├── firebase/
│   └── repositories/
├── domain/
│   ├── usecases/
│   ├── repositories/
│   └── models/
├── presentation/
│   ├── screens/
│   ├── viewmodels/
│   ├── components/
│   ├── navigation/
│   └── theme/
├── di/
│   ├── AppModule.kt
│   ├── RepositoryModule.kt
│   ├── UseCaseModule.kt
│   └── DataSourceModule.kt
├── utils/
│   ├── Constants.kt
│   ├── Extensions.kt
│   └── Validators.kt
└── MainActivity.kt
```

---

This architecture ensures:
- ✅ **Separation of Concerns**: Each layer has specific responsibilities
- ✅ **Testability**: Dependencies can be easily mocked and tested
- ✅ **Maintainability**: Clear structure and organization
- ✅ **Scalability**: Easy to add new features
- ✅ **Reusability**: Components can be reused across the app
