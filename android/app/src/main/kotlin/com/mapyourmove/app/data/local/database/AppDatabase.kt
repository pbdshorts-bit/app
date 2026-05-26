package com.mapyourmove.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mapyourmove.app.data.local.entities.ItineraryEntity
import com.mapyourmove.app.data.local.entities.BookingEntity
import com.mapyourmove.app.data.local.entities.LocationEntity
import com.mapyourmove.app.data.local.dao.ItineraryDao
import com.mapyourmove.app.data.local.dao.BookingDao
import com.mapyourmove.app.data.local.dao.LocationDao

@Database(
    entities = [
        ItineraryEntity::class,
        BookingEntity::class,
        LocationEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itineraryDao(): ItineraryDao
    abstract fun bookingDao(): BookingDao
    abstract fun locationDao(): LocationDao
}
