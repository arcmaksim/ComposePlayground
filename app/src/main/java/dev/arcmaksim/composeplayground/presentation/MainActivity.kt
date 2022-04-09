package dev.arcmaksim.composeplayground.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import dev.arcmaksim.composeplayground.presentation.ui.draganddrop.DieDragAndDropScreen
import dev.arcmaksim.composeplayground.presentation.ui.draganddrop.DragAndDropViewModel
import dev.arcmaksim.composeplayground.presentation.ui.theme.ComposePlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: DragAndDropViewModel by viewModels()

        setContent {
            ComposePlaygroundTheme {
                val state by viewModel.state.observeAsState()
                DieDragAndDropScreen(
                    state = state!!,
                    onDragCompleted = viewModel::consumeDie,
                )
            }
        }
    }
}
