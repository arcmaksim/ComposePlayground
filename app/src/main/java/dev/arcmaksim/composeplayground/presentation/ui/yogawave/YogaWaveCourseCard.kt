package dev.arcmaksim.composeplayground.presentation.ui.yogawave

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.arcmaksim.composeplayground.R
import dev.arcmaksim.presentation.theme.ComposePlaygroundTheme
import dev.arcmaksim.presentation.theme.Shapes
import dev.arcmaksim.presentation.theme.Typography

@Composable
fun YogaWaveCourseCard(
    currentProgress: Int,
    totalProgress: Int,
    category: String,
    title: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(16.dp),
        backgroundColor = Color.Gray,
        shape = Shapes.medium.copy(all = CornerSize(16.dp)),
    ) {
        Image(
            painter = painterResource(R.drawable.yoga_pose),
            contentDescription = stringResource(R.string.yoga_wave_course_banner),
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier.padding(
                top = 32.dp,
                bottom = 24.dp,
                start = 24.dp,
                end = 24.dp,
            ),
        ) {
            YogaWaveCourseProgress(
                currentProgress = currentProgress,
                totalProgress = totalProgress,
                rowSize = 4,
            )
            Spacer(
                modifier = Modifier.weight(1f),
            )
            Text(
                text = category,
                color = Color.White,
                style = Typography.body2,
            )
            Text(
                text = title,
                color = Color.White,
                style = Typography.h3,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun YogaWaveCourseCardPreview() {
    ComposePlaygroundTheme {
        YogaWaveCourseCard(
            currentProgress = 2,
            totalProgress = 15,
            category = "Weight Loss",
            title = "First Class",
        )
    }
}
