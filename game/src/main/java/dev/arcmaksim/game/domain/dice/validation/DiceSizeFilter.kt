package dev.arcmaksim.game.domain.dice.validation

import dev.arcmaksim.game.domain.dice.DiceSize

class DiceSizeFilter private constructor(
    val size: DiceSize,
) {

    companion object {

        val D4SizeFilter = DiceSizeFilter(DiceSize.D4)

        val D6SizeFilter = DiceSizeFilter(DiceSize.D6)

        val D8SizeFilter = DiceSizeFilter(DiceSize.D8)

        val D10SizeFilter = DiceSizeFilter(DiceSize.D10)

        val D12SizeFilter = DiceSizeFilter(DiceSize.D12)

        val D20SizeFilter = DiceSizeFilter(DiceSize.D20)

    }

}
