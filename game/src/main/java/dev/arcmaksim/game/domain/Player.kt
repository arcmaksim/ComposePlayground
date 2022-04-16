package dev.arcmaksim.game.domain

import dev.arcmaksim.game.domain.dice.Dice
import dev.arcmaksim.game.domain.dice.DiceSize
import dev.arcmaksim.game.domain.dice.DiceType
import dev.arcmaksim.game.domain.dice.produceDice

data class Player(
    val health: Int,
    val attack: Int,
    val defence: Int,
    val xp: Int,
    val powerDice: Map<DiceSize, Int>,
    val mentalDice: Map<DiceSize, Int>,
    val cunningDice: Map<DiceSize, Int>,
    val availableDice: List<Dice>,
) {

    constructor(
        health: Int,
        attack: Int,
        defence: Int,
        xp: Int,
        powerDice: Map<DiceSize, Int>,
        mentalDice: Map<DiceSize, Int>,
        cunningDice: Map<DiceSize, Int>,
    ) : this(
        health = health,
        attack = attack,
        defence = defence,
        xp = xp,
        powerDice = powerDice,
        mentalDice = mentalDice,
        cunningDice = cunningDice,
        availableDice = powerDice.flatMap { (key, value) -> List(value) { key.produceDice(DiceType.POWER) } }
            + mentalDice.flatMap { (key, value) -> List(value) { key.produceDice(DiceType.MENTAL) } }
            + cunningDice.flatMap { (key, value) -> List(value) { key.produceDice(DiceType.CUNNING) } },
    )

}
