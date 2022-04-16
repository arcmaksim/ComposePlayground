package dev.arcmaksim.game.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.arcmaksim.game.CreatureFactory
import dev.arcmaksim.game.PlayerFactory
import dev.arcmaksim.game.domain.dice.Dice
import dev.arcmaksim.game.domain.dice.DiceSize
import dev.arcmaksim.game.domain.ability.Ability
import dev.arcmaksim.game.domain.dice.DiceType
import dev.arcmaksim.game.domain.dice.produceDice
import dev.arcmaksim.presentation.theme.ComposePlaygroundTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameScreen(
    gameState: GameState,
    onGameStarted: () -> Unit,
    onGamePaused: () -> Unit,
    onMoveMade: (GameState.Move) -> Unit,
    endTurn: () -> Unit,
    placeDie: (String, Dice) -> Unit,
) {
    var direction by remember { mutableStateOf<GameState.Move?>(null) }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        /*.pointerInput(Unit) {
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
        }*/
        color = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            /*if (gameState.status == GameState.Status.Paused) {
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
            }*/
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
                "Turn count: ${gameState.turnCount}",
            )
            DraggableContent(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column {
                    gameState.playerAbilities.map {
                        PlayerAbilityItem(
                            playerAbility = it,
                            onDiePlaced = placeDie,
                        )
                    }
                    /*Row {
                        gameState.availableDice.map {
                            Draggable(value = it) {
                                Dice(
                                    dieSize = 72.dp,
                                    dice = it,
                                )
                            }
                        }
                    }*/
                }
            }
            Button(
                onClick = endTurn,
            ) {
                Text(
                    "End turn",
                )
            }
        }
    }
}

@Composable
fun PlayerAbilityItem(
    playerAbility: Ability,
    onDiePlaced: (String, Dice) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(12.dp),
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        /*DropTarget<Dice>(
            onContentDropped = { onDiePlaced(playerAbility.id, it) },
        ) {
            Dice(
                dieSize = 72.dp,
                dice = playerAbility.placedDice
            )
        }
        Text(
            text = playerAbility.description,
            color = Color.White,
        )*/
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    val creatureFactory = CreatureFactory()
    val playerFactory = PlayerFactory()

    ComposePlaygroundTheme {
        GameScreen(
            gameState = GameState(
                status = GameState.Status.Paused,
                lastUpdateTime = System.currentTimeMillis(),
                turnCount = 0,
                position = 0,
                turnTime = 3000L,
                turnTimeRemaining = 3000L,
                creature = creatureFactory.productSkeletonWarrior(),
                player = playerFactory.produceDefaultPlayer(),
                playerAbilities = listOf(playerFactory.attackAbility),
            ),
            onGameStarted = { Unit },
            onGamePaused = { Unit },
            onMoveMade = { _ -> Unit },
            endTurn = { Unit },
            placeDie = { _, _ -> Unit },
        )
    }
}
