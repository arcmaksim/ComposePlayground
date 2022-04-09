package dev.arcmaksim.composeplayground.presentation.ui.draganddrop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import dev.arcmaksim.composeplayground.presentation.ui.theme.ComposePlaygroundTheme

@Composable
fun DieSlot(
    modifier: Modifier = Modifier,
    die: DieEntity? = null,
    backgroundColor: Color = Color(0xA0BBBBBB),
    dieSize: Dp = 120.dp,
) {
    Box(
        modifier = modifier
            .size(dieSize)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp),
            ),
        contentAlignment = Alignment.Center,
    ) {
        die?.let {
            Die(die = it)
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
