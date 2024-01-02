package com.example.myapplicationv2


import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "pracivnuk")
data class Pracivnuk(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name: String,
    val zarplata: Int,
    val position: String

    )


