package com.mapyourmove.app.domain.models

import java.util.Date

data class Booking(
    val id: String,
    val userId: String,
    val itineraryId: String,
    val bookingType: BookingType,
    val documentUrl: String,
    val documentName: String,
    val provider: String,
    val price: Double,
    val currency: String = "USD",
    val bookingDate: Date,
    val startDate: Date,
    val endDate: Date,
    val confirmationNumber: String = "",
    val status: BookingStatus = BookingStatus.CONFIRMED,
    val createdAt: Date = Date()
)

enum class BookingType {
    FLIGHT,
    HOTEL,
    TRAIN,
    CAR_RENTAL,
    OTHER
}

enum class BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    COMPLETED
}
