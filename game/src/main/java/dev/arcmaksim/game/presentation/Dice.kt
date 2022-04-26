package dev.arcmaksim.game.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.arcmaksim.game.domain.dice.Dice
import dev.arcmaksim.game.domain.dice.DiceSize
import dev.arcmaksim.game.domain.dice.DiceType
import dev.arcmaksim.presentation.theme.ComposePlaygroundTheme

@Composable
fun Dice(
    dice: Dice,
    modifier: Modifier = Modifier,
    dieSize: Dp = 120.dp,
    onSelected: (Dice) -> Unit,
) {
    val backgroundColor = dice.type.toColor()
    val textColor = Color.White

    Box(
        modifier = modifier
            .size(dieSize)
            .aspectRatio(1f)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "D${dice.size.value}",
                style = MaterialTheme.typography.h5,
                color = textColor,
            )
            Text(
                text = "${dice.value}",
                style = MaterialTheme.typography.h5,
                color = textColor,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultDicePreview() {
    ComposePlaygroundTheme {
        Dice(
            modifier = Modifier.padding(16.dp),
            dice = Dice(
                value = 4,
                type = DiceType.CUNNING,
                size = DiceSize.D6,
            ),
            onSelected = { Unit },
        )
    }
}
