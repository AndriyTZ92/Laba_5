package com.example.myapplicationv2

sealed interface PracivnukEvent {
    data object SavePracivnuk: PracivnukEvent
    data class SetName(val name: String): PracivnukEvent
    data class SetZarplata(val zarplata: String): PracivnukEvent
    data class SetPosition(val position: String): PracivnukEvent
    data class SetExperience(val experience: String): PracivnukEvent
    data class SetEfficiency(val efficiency: String?): PracivnukEvent
    data object ShowDialog: PracivnukEvent
    data object HideDialog: PracivnukEvent
    data class DeleteRabotnik(val rabotnik: Pracivnuk): PracivnukEvent
}