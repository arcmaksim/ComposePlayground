package dev.arcmaksim.composeplayground.presentation.ui.draganddrop

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.arcmaksim.composeplayground.presentation.ui.draganddrop.model.Die
import dev.arcmaksim.composeplayground.presentation.ui.draganddrop.model.DieValue
import dev.arcmaksim.composeplayground.presentation.ui.theme.ComposePlaygroundTheme

@Composable
fun DieDragAndDropScreen(
    dice: List<Die?>,
    onDragCompleted: (Int, Int) -> Unit,
) {
    DraggableContent(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            DiceRow(
                dice = dice.take(3),
            ) { slotIndex, die ->
                DieCell(
                    die = die,
                    cellIndex = slotIndex,
                    onDragCompleted = onDragCompleted,
                )
            }
            DiceRow(
                dice = dice.take(6).takeLast(3),
            ) { slotIndex, die ->
                DieCell(
                    die = die,
                    cellIndex = slotIndex + 3,
                    onDragCompleted = onDragCompleted,
                )
            }
            DiceRow(
                dice = dice.takeLast(3),
            ) { slotIndex, die ->
                DieCell(
                    die = die,
                    cellIndex = slotIndex + 6,
                    onDragCompleted = onDragCompleted,
                )
            }
        }
    }
}

@Composable
fun DiceRow(
    dice: List<Die?>,
    dieSlot: @Composable (Int, Die?) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        dice.mapIndexed { index, die -> dieSlot(index, die) }
    }
}

@Composable
fun DieCell(
    die: Die?,
    cellIndex: Int,
    onDragCompleted: (Int, Int) -> Unit,
) {
    DropTarget<Int>(
        enabled = die == null,
        onContentDropped = { indexFrom -> onDragCompleted(indexFrom, cellIndex) },
    ) { dragAndDropReady ->
        Log.d("ASD", "DropTarget of cell $cellIndex is ${die == null}")
        Draggable(
            value = cellIndex,
            enabled = die != null,
        ) {
            Log.d("ASD", "Draggable of cell $cellIndex is ${die != null}")
            DieSlot(
                die = die,
                dragAndDropReady = dragAndDropReady,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DieDragAndDropScreenPreview() {
    ComposePlaygroundTheme {
        DieDragAndDropScreen(
            dice = arrayOf(
                Die(DieValue.Six), null, Die(DieValue.Three),
                null, null, null,
                Die(DieValue.Five), null, null,
            ).toList(),
            onDragCompleted = { _, _ -> Unit },
        )
    }
}
