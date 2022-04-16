package dev.arcmaksim.composeplayground.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.arcmaksim.game.presentation.GameScreen
import dev.arcmaksim.game.presentation.GameViewModel
import dev.arcmaksim.presentation.theme.ComposePlaygroundTheme

class MainActivity : ComponentActivity() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                val gameState by viewModel.state.collectAsState()
                GameScreen(
                    gameState = gameState,
                    onGamePaused = { viewModel.pauseGame() },
                    onGameStarted = { viewModel.startGame() },
                    onMoveMade = { move -> viewModel.makeMove(move) },
                )
            }
        }
    }

}
