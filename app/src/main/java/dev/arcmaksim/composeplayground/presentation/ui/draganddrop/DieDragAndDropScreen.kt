package dev.arcmaksim.composeplayground.presentation.ui.draganddrop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.arcmaksim.composeplayground.presentation.ui.theme.ComposePlaygroundTheme

@Composable
fun DieDragAndDropScreen(
    state: State,
    onDragCompleted: (Int, Int) -> Unit,
) {
    LongPressDraggable(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                state.availableDice.map {
                    DragTarget(
                        dataToDrop = it,
                    ) {
                        Die(die = it)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                state.placedDice.map {
                    DropTarget<DieEntity> { isInBound, data ->
                        val bgColor = if (isInBound) {
                            Color.Red
                        } else {
                            Color(0xA0BBBBBB)
                        }
                        DieSlot(
                            die = data,
                            backgroundColor = bgColor,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DieDragAndDropScreenPreview() {
    ComposePlaygroundTheme {
        DieDragAndDropScreen(
            state = State(
                availableDice = listOf(DieEntity.Six, DieEntity.One, DieEntity.Three),
                placedDice = listOf(null, null, null),
            ),
            onDragCompleted = { _, _ -> Unit },
        )
    }
}
