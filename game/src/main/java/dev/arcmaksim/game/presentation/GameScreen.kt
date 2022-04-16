package dev.arcmaksim.game.presentation

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.arcmaksim.game.CreatureFactory
import dev.arcmaksim.presentation.theme.ComposePlaygroundTheme
import kotlin.math.abs

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameScreen(
    gameState: GameState,
    onGameStarted: () -> Unit,
    onGamePaused: () -> Unit,
    onMoveMade: (GameState.Move) -> Unit,
) {
    var direction by remember { mutableStateOf<GameState.Move?>(null) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        direction?.let { onMoveMade(it) }
                        direction = null
                    }
                ) { change, dragAmount ->
                    change.consumeAllChanges()
                    val (x, y) = dragAmount
                    if (abs(x) > abs(y)) {
                        when {
                            x > 0 -> {
                                direction = GameState.Move.Right
                            }
                            x < 0 -> {
                                direction = GameState.Move.Left
                            }
                        }
                    } else {
                        when {
                            y > 0 -> {
                                direction = GameState.Move.Down
                            }
                            y < 0 -> {
                                direction = GameState.Move.Up
                            }
                        }
                    }
                }
            },
        color = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            EnemyCard(
                modifier = Modifier.padding(24.dp),
                creature = gameState.creature,
            )
            /*GameTurnTimer(
                modifier = Modifier.height(48.dp),
                remainingTime = gameState.turnTimeRemaining,
                turnPeriod = gameState.turnTime,
            )*/
            Text(
                "Position: ${gameState.position}",
            )
            Text(
                "Turn count: ${gameState.turnCount}",
            )
            if (gameState.status == GameState.Status.Paused) {
                Button(
                    onClick = onGameStarted,
                ) {
                    Text(
                        "Start Game",
                    )
                }
            }
            if (gameState.status == GameState.Status.Started) {
                Button(
                    onClick = onGamePaused,
                ) {
                    Text(
                        "Pause Game",
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    ComposePlaygroundTheme {
        GameScreen(
            gameState = GameState(
                status = GameState.Status.Paused,
                lastUpdateTime = System.currentTimeMillis(),
                turnCount = 0,
                position = 0,
                turnTime = 3000L,
                turnTimeRemaining = 3000L,
                creature = CreatureFactory().productSkeletonWarrior(),
            ),
            onGameStarted = { Unit },
            onGamePaused = { Unit },
            onMoveMade = { _ -> Unit },
        )
    }
}
