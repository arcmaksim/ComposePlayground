package dev.arcmaksim.game.domain.ability

import dev.arcmaksim.game.domain.dice.Dice

class ShutterAbility : Ability(
    description = "Shatters enemy defence by Dice -2 (min 1)",
    valueFilter = null,
    typeFilter = null,
    sizeFilter = null,
) {

    override fun use(
        dice: Dice,
    ): AbilityResult = AbilityResult(
        resultedDice = dice.divideValue(2),
        effect = None,
    )

}
