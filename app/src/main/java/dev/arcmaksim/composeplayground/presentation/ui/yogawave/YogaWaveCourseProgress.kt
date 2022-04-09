package dev.arcmaksim.composeplayground.presentation.ui.yogawave

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.arcmaksim.composeplayground.presentation.ui.theme.ComposePlaygroundTheme

@Composable
fun YogaWaveCourseProgress(
    currentProgress: Int,
    totalProgress: Int,
    rowSize: Int,
    modifier: Modifier = Modifier,
    itemSize: Dp = 12.dp,
    itemPadding: Dp = 8.dp,
) {
    val rowCount = if (totalProgress % rowSize == 0) {
        totalProgress / rowSize
    } else {
        totalProgress / rowSize + 1
    }

    Canvas(
        modifier = modifier.size(
            width = itemSize.times(rowSize) + itemPadding.times(rowSize - 1),
            height = itemSize.times(rowCount) + itemPadding.times(rowCount - 1),
        ),
    ) {
        val canvasItemSize = itemSize.roundToPx()
        val circleRadius = canvasItemSize / 2f
        val ringStroke = canvasItemSize / 4f
        val ringRadius = circleRadius - ringStroke / 2

        val step = (canvasItemSize + itemPadding.roundToPx()).toFloat()
        val offset = Offset(circleRadius, circleRadius)

        for (i in 0 until totalProgress) {
            val isActive = i <= currentProgress
            val horizontalPosition = i % rowSize
            val verticalPosition = i / rowSize
            translate(
                horizontalPosition * step,
                verticalPosition * step,
            ) {
                if (currentProgress == i) {
                    drawCircle(
                        color = YogaWaveInactiveProgressColor,
                        radius = circleRadius - ringStroke,
                        center = offset,
                    )
                    drawCircle(
                        color = YogaWaveActiveProgressColor,
                        radius = ringRadius,
                        style = Stroke(width = ringStroke),
                        center = offset,
                    )
                } else {
                    drawCircle(
                        color = if (isActive) YogaWaveActiveProgressColor else YogaWaveInactiveProgressColor,
                        radius = circleRadius,
                        center = offset,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun YogaWaveCourseProgressPreview() {
    ComposePlaygroundTheme {
        YogaWaveCourseProgress(
            currentProgress = 2,
            totalProgress = 15,
            rowSize = 4,
        )
    }
}
