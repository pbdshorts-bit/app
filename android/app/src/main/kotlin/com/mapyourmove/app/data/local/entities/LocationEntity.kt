package com.mapyourmove.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val city: String,
    val country: String,
    val rating: Double = 0.0,
    val reviewCount: Int = 0,
    val locationType: String = "ATTRACTION",
    val imageUrl: String? = null,
    val description: String = ""
)
