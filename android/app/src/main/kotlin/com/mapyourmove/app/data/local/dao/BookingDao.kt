package com.mapyourmove.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mapyourmove.app.data.local.entities.BookingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {
    @Insert
    suspend fun insertBooking(booking: BookingEntity)
    
    @Update
    suspend fun updateBooking(booking: BookingEntity)
    
    @Query("SELECT * FROM bookings WHERE userId = :userId ORDER BY startDate DESC")
    fun getUserBookings(userId: String): Flow<List<BookingEntity>>
    
    @Query("SELECT * FROM bookings WHERE itineraryId = :itineraryId")
    fun getItineraryBookings(itineraryId: String): Flow<List<BookingEntity>>
    
    @Query("DELETE FROM bookings WHERE id = :id")
    suspend fun deleteBooking(id: String)
}
