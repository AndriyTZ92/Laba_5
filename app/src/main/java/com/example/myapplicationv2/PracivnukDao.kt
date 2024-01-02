package com.example.myapplicationv2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PracivnukDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPracivnuk (pracivnuk: Pracivnuk)
    @Delete
    suspend fun deletePracivnuk (pracivnuk: Pracivnuk)
    @Query("SELECT * FROM pracivnuk")
    fun getAllRabotniks(): Flow<List<Pracivnuk>>

}