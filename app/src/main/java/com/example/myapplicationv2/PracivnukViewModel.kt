package com.example.myapplicationv2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PracivnukViewModel(
    private val dao: PracivnukDao
): ViewModel() {
    val _state = MutableStateFlow(PracivnukState())

    init {
        viewModelScope.launch {
            dao.getAllRabotniks().collect { pracivnuks ->
                _state.value = _state.value.copy(pracivnuk = pracivnuks)
            }
        }
    }

    fun onEvent(event: PracivnukEvent) {
        when (event) {
            is PracivnukEvent.DeleteRabotnik -> {
                viewModelScope.launch {
                    dao.deletePracivnuk(event.rabotnik)
                }
            }
            PracivnukEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingRabotnik = false
                    )
                }
            }
            PracivnukEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingRabotnik = true
                    )
                }
            }
            PracivnukEvent.SavePracivnuk -> {
                val name = _state.value.name
                val position = _state.value.position
                val experience = _state.value.experience.toIntOrNull() ?: 0
                val efficiency = _state.value.efficiency.toDoubleOrNull() ?: 0.0
                var zarplata = _state.value.zarplata.toIntOrNull() ?: 0
                if (experience > 5) {
                    zarplata = (zarplata * 1.2 + 500).toInt()
                } else if (experience > 2) {
                    zarplata += 200
                }
                if (position == "Designer" && efficiency != null) {
                    zarplata = (zarplata * efficiency).toInt()
                }
                if (name.isBlank()) {
                    return
                }
                val rabotnik = Pracivnuk(
                    name = name,
                    zarplata = zarplata,
                    position = position
                )
                viewModelScope.launch {
                    dao.insertPracivnuk(rabotnik)
                }
                _state.update {
                    it.copy(
                        isAddingRabotnik = false,
                        name = "",
                        zarplata = "",
                        position = "Developer",
                        experience = "",
                        efficiency = ""
                    )
                }
            }
            is PracivnukEvent.SetName -> {
                _state.update {
                    it.copy(
                        name = event.name
                    )
                }
            }
            is PracivnukEvent.SetZarplata -> {
                _state.update {
                    it.copy(
                        zarplata = event.zarplata
                    )
                }
            }
            is PracivnukEvent.SetPosition -> {
                _state.update {
                    it.copy(
                        position = event.position
                    )
                }
            }
            is PracivnukEvent.SetExperience -> {
                _state.update {
                    it.copy(
                        experience = event.experience
                    )
                }
            }
            is PracivnukEvent.SetEfficiency -> {
                _state.update {
                    it.copy(
                        efficiency = event.efficiency ?: ""
                    )
                }
            }

            is DeletePracivnuk -> TODO()
        }
    }
}
