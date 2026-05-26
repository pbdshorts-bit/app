package com.mapyourmove.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mapyourmove.app.data.local.entities.ItineraryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItineraryDao {
    @Insert
    suspend fun insertItinerary(itinerary: ItineraryEntity)
    
    @Update
    suspend fun updateItinerary(itinerary: ItineraryEntity)
    
    @Query("SELECT * FROM itineraries WHERE id = :id")
    fun getItineraryById(id: String): Flow<ItineraryEntity?>
    
    @Query("SELECT * FROM itineraries WHERE userId = :userId ORDER BY createdAt DESC")
    fun getUserItineraries(userId: String): Flow<List<ItineraryEntity>>
    
    @Query("DELETE FROM itineraries WHERE id = :id")
    suspend fun deleteItinerary(id: String)
}
