package com.mapyourmove.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "itineraries")
data class ItineraryEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val destination: String,
    val startDate: Date,
    val endDate: Date,
    val description: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
