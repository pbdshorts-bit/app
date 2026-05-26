package com.mapyourmove.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mapyourmove.app.data.local.entities.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Insert
    suspend fun insertLocation(location: LocationEntity)
    
    @Query("SELECT * FROM locations WHERE country = :country ORDER BY rating DESC")
    fun getLocationsByCountry(country: String): Flow<List<LocationEntity>>
    
    @Query("SELECT * FROM locations WHERE city = :city ORDER BY rating DESC")
    fun getLocationsByCity(city: String): Flow<List<LocationEntity>>
    
    @Query("SELECT * FROM locations WHERE locationType = :type ORDER BY rating DESC LIMIT :limit")
    fun getLocationsByType(type: String, limit: Int = 10): Flow<List<LocationEntity>>
}
