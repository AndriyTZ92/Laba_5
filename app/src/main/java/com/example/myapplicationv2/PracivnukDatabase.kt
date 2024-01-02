package com.example.myapplicationv2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Pracivnuk::class] , version = 1, exportSchema = true)
abstract class PracivnukDatabase: RoomDatabase() {
    abstract val dao: PracivnukDao
}
