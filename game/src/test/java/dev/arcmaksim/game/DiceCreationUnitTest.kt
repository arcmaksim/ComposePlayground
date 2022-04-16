package dev.arcmaksim.game

import dev.arcmaksim.game.domain.dice.Dice
import dev.arcmaksim.game.domain.dice.DiceSize
import dev.arcmaksim.game.domain.dice.DiceType
import dev.arcmaksim.game.domain.dice.produceDice
import org.junit.Test

import org.junit.Assert.*

class DiceCreationUnitTest {

    @Test
    fun `valid explicit plain dice creation`() {
        // Given
        val type = DiceType.POWER
        val size = DiceSize.D6
        val value = 6

        // When creating dice
        val createdDice = Dice(
            size = size,
            type = type,
            value = value,
        )

        // Then expect equal
        assertEquals(createdDice.value, value)
        assertEquals(createdDice.size, size)
        assertEquals(createdDice.type, type)
    }

    @Test
    fun `valid implicit plain dice creation`() {
        // Given
        val type = DiceType.POWER
        val size = DiceSize.D6

        // When creating dice
        val createdDice = Dice(
            size = size,
            type = type,
        )

        // Then expect equal
        assertEquals(createdDice.type, type)
        assertEquals(createdDice.size, size)
        assertEquals(createdDice.value, 6)
    }

    @Test
    fun `invalid explicit plain dice creation`() {
        // Given
        val type = DiceType.POWER
        val size = DiceSize.D6
        val value = 7

        // When creating dice
        // Then expect exception
        assertThrows(IllegalArgumentException::class.java) {
            Dice(
                value = value,
                size = size,
                type = type,
            )
        }
    }

    @Test
    fun `valid dice creation by size`() {
        // Given
        val type = DiceType.POWER
        val size = DiceSize.D6

        // When creating dice
        val createdDice = size.produceDice(type)

        // Then expect equal
        assertEquals(createdDice.type, type)
        assertEquals(createdDice.size, size)
        assertEquals(createdDice.value, 6)
    }

}
