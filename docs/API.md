# Map Your Move API Documentation

## External APIs

### Google Maps API

**Endpoints Used:**
- Places API - Location search, details, photos
- Maps API - Map rendering, navigation
- Geocoding API - Address to coordinates

**Key Methods:**
```kotlin
// Initialize Google Maps
GoogleMapsManager.initialize(apiKey)

// Search places
PlacesManager.searchNearby(latitude, longitude, radius, type)

// Get place details
PlacesManager.getPlaceDetails(placeId)
```

### OpenWeatherMap API

**Base URL:** `https://api.openweathermap.org/`

**Current Weather:**
```
GET /data/2.5/weather?lat={lat}&lon={lon}&appid={key}
```

**Forecast:**
```
GET /data/2.5/forecast?lat={lat}&lon={lon}&appid={key}
```

### Firebase APIs

**Authentication:**
- Email/Password
- Google Sign-In
- Apple Sign-In

**Firestore:**
- Users collection
- Itineraries collection
- Bookings collection
- Locations collection

**Storage:**
- Upload tickets
- Store images

## Internal APIs

### Authentication Service

```kotlin
class AuthRepository {
    suspend fun loginWithEmail(email: String, password: String): Result<User>
    suspend fun signupWithEmail(name: String, email: String, password: String): Result<User>
    suspend fun loginWithGoogle(token: String): Result<User>
    suspend fun loginWithApple(token: String): Result<User>
    suspend fun logout(): Result<Unit>
}
```

### Itinerary Service

```kotlin
class ItineraryRepository {
    suspend fun createItinerary(itinerary: Itinerary): Result<String>
    suspend fun updateItinerary(itinerary: Itinerary): Result<Unit>
    suspend fun getItinerary(id: String): Result<Itinerary>
    suspend fun getUserItineraries(userId: String): Result<List<Itinerary>>
    suspend fun deleteItinerary(id: String): Result<Unit>
}
```

### Booking Service

```kotlin
class BookingRepository {
    suspend fun uploadBooking(booking: Booking, file: File): Result<String>
    suspend fun getBookings(userId: String): Result<List<Booking>>
    suspend fun getItineraryBookings(itineraryId: String): Result<List<Booking>>
    suspend fun deleteBooking(id: String): Result<Unit>
}
```

### Location Service

```kotlin
class LocationRepository {
    suspend fun searchLocations(query: String): Result<List<Location>>
    suspend fun getLocationsByCountry(country: String): Result<List<Location>>
    suspend fun getHotelRecommendations(location: Location, budget: Double): Result<List<Hotel>>
    suspend fun getLocationDetails(id: String): Result<Location>
}
```

## Data Models

### User
```json
{
  "id": "string",
  "fullName": "string",
  "email": "string",
  "profileImageUrl": "string",
  "preferredLanguage": "string",
  "createdAt": "timestamp",
  "isVerified": "boolean"
}
```

### Itinerary
```json
{
  "id": "string",
  "userId": "string",
  "destination": "string",
  "startDate": "timestamp",
  "endDate": "timestamp",
  "activities": ["Activity"],
  "createdAt": "timestamp"
}
```

### Booking
```json
{
  "id": "string",
  "userId": "string",
  "itineraryId": "string",
  "bookingType": "enum",
  "documentUrl": "string",
  "price": "number",
  "startDate": "timestamp",
  "endDate": "timestamp",
  "status": "enum"
}
```

## Error Handling

```kotlin
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception, val message: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
```

## Rate Limiting

Most APIs have rate limits:
- Weather API: 60 requests/minute
- Places API: Check Google Cloud quotas
- Firebase: Check pricing tier

## Authentication

All Firebase calls use:
- User's UID from Firebase Auth
- JWT token in Authorization header
- Automatic token refresh

---

For more details, see specific service documentation in the codebase.
