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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Whenever rotationRatio changes (which is many times a second), the Box composable will be recomposed since it uses a
 * state that changed (which is the rotation ratio).
 */
@Composable
fun TransformUsingRotateModifier() {
    Box(modifier = Modifier.fillMaxSize()) {
        val transition = rememberInfiniteTransition(label = "Infinite transition")
        val rotationDegree by transition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(animation = tween(3000)),
            label = "Infinite animation"
        )
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .rotate(rotationDegree * 360f)
                .size(100.dp)
                .background(Color.Gray)
        )
    }
}