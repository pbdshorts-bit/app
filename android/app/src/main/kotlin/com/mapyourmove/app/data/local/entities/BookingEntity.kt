package com.mapyourmove.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "bookings")
data class BookingEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val itineraryId: String,
    val bookingType: String,
    val documentUrl: String,
    val documentName: String,
    val provider: String,
    val price: Double,
    val currency: String = "USD",
    val bookingDate: Date,
    val startDate: Date,
    val endDate: Date,
    val confirmationNumber: String = "",
    val status: String = "CONFIRMED",
    val createdAt: Date = Date()
)
