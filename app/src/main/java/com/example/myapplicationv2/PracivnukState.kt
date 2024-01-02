package com.example.myapplicationv2

data class PracivnukState(
    val pracivnuk: List<Pracivnuk> = emptyList(),
    val name: String = "",
    val zarplata: String = "",
    val position: String = "Developer",
    val experience: String = "",
    val efficiency: String = "",
    val isAddingRabotnik: Boolean = false, // Add new Pracivnuk
)
