package dev.arcmaksim.game.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.arcmaksim.game.domain.dice.Dice
import dev.arcmaksim.presentation.theme.ComposePlaygroundTheme

@Composable
fun DieSlot(
    modifier: Modifier = Modifier,
    dice: Dice? = null,
    dragAndDropReady: Boolean = false,
    dieSize: Dp = 120.dp,
) {
    val backgroundColor = if (dragAndDropReady) {
        Color.Red
    } else {
        Color(0xA0BBBBBB)
    }

    Box(
        modifier = modifier
            .size(dieSize)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp),
            ),
        contentAlignment = Alignment.Center,
    ) {
        dice?.let {
            Dice(dice = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DieSlotPreview() {
    ComposePlaygroundTheme {
        DieSlot(
            modifier = Modifier.padding(16.dp),
        )
    }
}