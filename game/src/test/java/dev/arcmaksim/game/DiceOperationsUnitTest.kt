package dev.arcmaksim.game

import dev.arcmaksim.game.domain.dice.Dice
import dev.arcmaksim.game.domain.dice.DiceSize
import dev.arcmaksim.game.domain.dice.DiceType
import dev.arcmaksim.game.domain.dice.produceDice
import org.junit.Test

import org.junit.Assert.*

class DiceOperationsUnitTest {

    @Test
    fun `valid dice value adding with max value`() {
        // Given dice with max value
        val createdDice = Dice(
            size = DiceSize.D6,
            type = DiceType.POWER,
        )

        // When adding a value
        val resultedValue = createdDice.addValue(2)

        // Then expect that values are equal
        assertEquals(createdDice.value, resultedValue.value)
    }

    @Test
    fun `valid dice value adding with non-max value`() {
        // Given dice with non-max value
        val initialValue = 1
        val valueToAdd = 3
        val createdDice = Dice(
            size = DiceSize.D6,
            type = DiceType.POWER,
            value = initialValue,
        )

        // When adding a value
        val resultedValue = createdDice.addValue(valueToAdd)

        // Then expect that values are equal
        assertEquals(resultedValue.value, initialValue + valueToAdd)
    }

}
