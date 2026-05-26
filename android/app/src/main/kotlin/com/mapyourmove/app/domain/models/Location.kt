package com.mapyourmove.app.domain.models

data class Location(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val city: String,
    val country: String,
    val rating: Double = 0.0,
    val reviewCount: Int = 0,
    val locationType: LocationType = LocationType.ATTRACTION,
    val imageUrl: String? = null,
    val description: String = ""
)

enum class LocationType {
    HOTEL,
    RESTAURANT,
    MONUMENT,
    BEACH,
    ATTRACTION,
    MUSEUM,
    PARK,
    OTHER
}
