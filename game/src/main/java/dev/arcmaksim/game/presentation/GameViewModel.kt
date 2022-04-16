package dev.arcmaksim.game.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.arcmaksim.game.CreatureFactory
import dev.arcmaksim.game.PlayerFactory
import dev.arcmaksim.game.domain.Creature
import dev.arcmaksim.game.domain.dice.Dice
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.random.Random

private const val turnTime = 3000L

@OptIn(ExperimentalCoroutinesApi::class)
class GameViewModel : ViewModel() {

    private val creatureFactory = CreatureFactory()
    private val playerFactory = PlayerFactory()

    private var gameState = GameState(
        status = GameState.Status.Started,
        lastUpdateTime = System.currentTimeMillis(),
        turnCount = 0,
        position = 0,
        turnTime = turnTime,
        turnTimeRemaining = turnTime,
        creature = produceRandomCreature(),
        player = playerFactory.produceDefaultPlayer(),
        playerAbilities = listOf(playerFactory.attackAbility, playerFactory.healAbility, playerFactory.shutterAbility),
    )

    private val _state = MutableStateFlow(gameState)
    val state = _state.asStateFlow()

    private var gamePendingStatus: GameState.Status? = null

    fun startGame() {
        gamePendingStatus = GameState.Status.Started
    }

    fun pauseGame() {
        gamePendingStatus = GameState.Status.Paused
    }

    fun makeMove(
        move: GameState.Move,
    ) {
        if (gameState.status == GameState.Status.Paused) return

        val size = 5
        val positionModifier = when (move) {
            GameState.Move.Down -> -size
            GameState.Move.Up -> size
            GameState.Move.Right -> 1
            GameState.Move.Left -> -1
        }

        gameState.let {
            gameState = it.copy(
                position = it.position + positionModifier,
                turnTimeRemaining = turnTime,
                turnCount = it.turnCount + 1,
                creature = produceRandomCreature(),
            )
        }
    }

    fun placeDie(
        abilityId: String,
        dice: Dice,
    ) {
        /*gameState.let { state ->
            gameState = state.copy(
                availableDice = state.availableDice - dice,
                playerAbilities = state.playerAbilities.map { if (it.id == abilityId) it.copy(placedDice = dice) else it },
            )
        }*/
    }

    fun endTurn() {
        /*gameState.let { state ->
            gameState = state.copy(
                availableDice = state.player.totalDice.map(::produceDie),
                playerAbilities = state.playerAbilities.map { it.copy(placedDice = null) },
            )
        }*/
    }

    init {
        viewModelScope.launch {
            channelFlow<Unit> {
                while (!isClosedForSend) {
                    _state.value = reduceGameState(System.currentTimeMillis())
                    delay(1)
                }
            }.collect()
        }
    }

    private fun reduceGameState(
        currentTime: Long,
    ): GameState {
        if (gameState.status == GameState.Status.Paused && gamePendingStatus != GameState.Status.Started) {
            clearGamePendingStatus()
            return gameState
        }

        gamePendingStatus?.let { pendingStatus ->
            clearGamePendingStatus()
            return gameState.copy(
                status = pendingStatus,
                lastUpdateTime = currentTime,
            ).also {
                gameState = it
            }
        }

        /*val tickTimeDiff = currentTime - gameState.lastUpdateTime
        val remainingTime = gameState.turnTimeRemaining - tickTimeDiff
        val isTurnEnded = remainingTime <= 0
        val turnCount = if (isTurnEnded) gameState.turnCount + 1 else gameState.turnCount*/

        return gameState.copy(
            lastUpdateTime = currentTime,
            //turnCount = turnCount,
            //turnTimeRemaining = if (isTurnEnded) turnTime else remainingTime,
        ).also {
            gameState = it
        }
    }

    private fun clearGamePendingStatus() {
        gamePendingStatus = null
    }

    private fun produceRandomCreature(): Creature = when(Random.nextInt(3)) {
        0 -> creatureFactory.productSkeletonWarrior()
        1 -> creatureFactory.productSkeletonArcher()
        else -> creatureFactory.productSkeletonWarlord()
    }

}




