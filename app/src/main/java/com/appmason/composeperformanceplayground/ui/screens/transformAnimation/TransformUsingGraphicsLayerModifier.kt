package com.appmason.composeperformanceplayground.ui.screens.transformAnimation

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

/**
 * Composables shouldn’t be recomposed if their appearance doesn’t change. The composable will look
 * exactly the same after a rotation, it’s just a bit rotated.
 *
 * Therefore, you can make use of the graphicsLayer modifier which will have the same effect,
 * but won’t cause these hundreds of recompositions.
 *
 * Therefore: If clipping, transform or alpha changes -> use graphicsLayer
 */
@Composable
fun TransformUsingGraphicsLayerModifier() {
    Box(modifier = Modifier.fillMaxSize()) {
        val transition = rememberInfiniteTransition(label = "Infinite transition")
        val rotationRatio by transition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(animation = tween(3000)),
            label = "Infinite animation"
        )
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer {
                    rotationZ = rotationRatio * 360f
                }
                .size(100.dp)
                .background(Color.Gray)
        )
    }
}