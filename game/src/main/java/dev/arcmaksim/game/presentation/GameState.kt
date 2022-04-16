package dev.arcmaksim.game.presentation

import dev.arcmaksim.game.domain.Creature
import dev.arcmaksim.game.domain.dice.Dice
import dev.arcmaksim.game.domain.Player
import dev.arcmaksim.game.domain.ability.Ability

data class GameState(
    val status: Status,
    val lastUpdateTime: Long,
    val position: Int,
    val turnCount: Int,
    val turnTime: Long,
    val turnTimeRemaining: Long,
    val creature: Creature,
    val player: Player,
    val playerAbilities: List<Ability>,
) {

    enum class Status {
        Started,
        Paused
    }

    enum class Move {
        Up,
        Down,
        Left,
        Right
    }

}
