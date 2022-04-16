package dev.arcmaksim.game.domain.dice

import kotlin.random.Random

fun DiceSize.produceDice(
    type: DiceType,
): Dice = Dice(
    value = value,
    size = this,
    type = type,
)

fun DiceSize.produceRandomDice(
    type: DiceType,
): Dice = Dice(
    value = Random.nextInt(1, value + 1),
    size = this,
    type = type,
)
