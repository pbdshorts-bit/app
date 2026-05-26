package com.mapyourmove.app.domain.models

data class Hotel(
    val id: String,
    val name: String,
    val location: Location,
    val rating: Double,
    val price: Double,
    val currency: String = "USD",
    val amenities: List<String> = emptyList(),
    val imageUrls: List<String> = emptyList(),
    val description: String = "",
    val checkInDate: String = "",
    val checkOutDate: String = "",
    val bookingUrl: String = ""
)
