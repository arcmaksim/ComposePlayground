package dev.arcmaksim.game.presentation

import android.util.Log
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

/**
 * Based on https://github.com/cp-radhika-s/Drag_and_drop_jetpack_compose
 */

internal val LocalDragTargetInfo = compositionLocalOf { DragTargetInfo() }

@Composable
fun DraggableContent(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val state = remember { DragTargetInfo() }

    CompositionLocalProvider(
        LocalDragTargetInfo provides state,
    ) {
        Box(
            modifier = modifier,
        ) {
            content()

            if (state.isDragging) {
                var targetSize by remember { mutableStateOf(IntSize.Zero) }

                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            val offset = (state.dragPosition + state.dragOffset)
                            alpha = if (targetSize == IntSize.Zero) 0f else .9f
                            translationX = offset.x.minus(targetSize.width / 2)
                            translationY = offset.y.minus(targetSize.height / 2)
                        }
                        .onGloballyPositioned {
                            targetSize = it.size
                        },
                ) {
                    state.draggableComposable?.invoke()
                }
            }
        }
    }
}

@Composable
fun <T> Draggable(
    value: T,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable (() -> Unit),
) {
    var currentPosition by remember { mutableStateOf(Offset.Zero) }
    val currentState = LocalDragTargetInfo.current

    Box(
        modifier = modifier
            .onGloballyPositioned {
                currentPosition = it.localToWindow(Offset.Zero)
            }
            .pointerInput(Unit) {
                if (enabled) {
                    detectDragGestures(
                        onDragStart = {
                            Log.d("ASD", "Drag started with value $value")
                            currentState.value = value
                            currentState.isDragging = true
                            currentState.dragPosition = currentPosition + it
                            currentState.draggableComposable = content
                        },
                        onDrag = { change, dragAmount ->
                            change.consumeAllChanges()
                            currentState.dragOffset += dragAmount
                        },
                        onDragEnd = {
                            currentState.isDragging = false
                            currentState.dragOffset = Offset.Zero
                        },
                        onDragCancel = {
                            currentState.dragOffset = Offset.Zero
                            currentState.isDragging = false
                        }
                    )
                }
            }
    ) {
        content()
    }
}

@Composable
fun <T> DropTarget(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onContentDropped: (T) -> Unit,
    content: @Composable (BoxScope.(dragAndDropReady: Boolean) -> Unit),
) {
    val dragInfo = LocalDragTargetInfo.current
    val dragPosition = dragInfo.dragPosition
    val dragOffset = dragInfo.dragOffset
    var isCurrentDropTarget by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.onGloballyPositioned {
            it.boundsInWindow().let { rect ->
                if (enabled) isCurrentDropTarget = rect.contains(dragPosition + dragOffset)
            }
        },
    ) {
        if (enabled and isCurrentDropTarget and !dragInfo.isDragging) {
            isCurrentDropTarget = false
            onContentDropped(dragInfo.value as T)
        }
        content(isCurrentDropTarget)
    }
}

internal class DragTargetInfo {
    var isDragging: Boolean by mutableStateOf(false)
    var dragPosition by mutableStateOf(Offset.Zero)
    var dragOffset by mutableStateOf(Offset.Zero)
    var draggableComposable by mutableStateOf<(@Composable () -> Unit)?>(null)
    var value by mutableStateOf<Any?>(null)
}
