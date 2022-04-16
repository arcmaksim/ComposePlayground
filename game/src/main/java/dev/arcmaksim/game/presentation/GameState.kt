package dev.arcmaksim.game.presentation

import dev.arcmaksim.game.domain.Creature

data class GameState(
    val status: Status,
    val lastUpdateTime: Long,
    val position: Int,
    val turnCount: Int,
    val turnTime: Long,
    val turnTimeRemaining: Long,
    val creature: Creature,
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
