package com.mapyourmove.app.domain.models

import java.util.Date

data class Itinerary(
    val id: String,
    val userId: String,
    val destination: String,
    val startDate: Date,
    val endDate: Date,
    val description: String = "",
    val activities: List<Activity> = emptyList(),
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

data class Activity(
    val id: String,
    val itineraryId: String,
    val title: String,
    val description: String,
    val date: Date,
    val location: Location,
    val time: String,
    val duration: Int, // in minutes
    val notes: String = ""
)
