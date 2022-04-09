package dev.arcmaksim.composeplayground.presentation.ui.draganddrop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DragAndDropViewModel : ViewModel() {

    private val _state = MutableLiveData(
        State(
            availableDice = listOf(DieEntity.Six, DieEntity.One, DieEntity.Three),
            placedDice = listOf(null, null, null),
        )
    )
    val state: LiveData<State> = _state

    fun consumeDie(
        indexFrom: Int,
        indexTo: Int,
    ) {
        val currentState = state.value!!
        val transferringDie = currentState.availableDice[indexFrom]
        val mutableAvailableDice = currentState.availableDice.toMutableList().apply {
            set(indexFrom, null)
        }
        val mutablePlacedDice = currentState.placedDice.toMutableList().apply {
            set(indexTo, transferringDie)
        }
        _state.value = State(
            availableDice = mutableAvailableDice.toList(),
            placedDice = mutablePlacedDice.toList(),
        )
    }

}

data class State(
    val availableDice: List<DieEntity?>,
    val placedDice: List<DieEntity?>,
)
