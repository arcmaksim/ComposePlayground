package dev.arcmaksim.composeplayground.presentation.ui.draganddrop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.arcmaksim.composeplayground.presentation.ui.draganddrop.model.Die
import dev.arcmaksim.composeplayground.presentation.ui.draganddrop.model.DieValue

class DragAndDropViewModel : ViewModel() {

    private val _state = MutableLiveData(
        listOf(
            Die(DieValue.Six), null, Die(DieValue.Three),
            null, null, null,
            Die(DieValue.Five), null, null,
        )
    )
    val state: LiveData<List<Die?>> = _state

    fun moveDie(
        indexFrom: Int,
        indexTo: Int,
    ) {
        _state.value = state.value!!.toTypedArray().run {
            val die = get(indexFrom)
            set(indexFrom, null)
            set(indexTo, die)
            toList()
        }
    }

}
