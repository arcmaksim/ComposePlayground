package dev.arcmaksim.game.domain

import androidx.annotation.DrawableRes

data class Creature(
    val name: String,
    @DrawableRes val iconResId: Int,
    val description: String,
    val difficulty: Int,
    val health: Int,
    val attack: Int,
    val defence: Int,
    val xp: Int,
)
