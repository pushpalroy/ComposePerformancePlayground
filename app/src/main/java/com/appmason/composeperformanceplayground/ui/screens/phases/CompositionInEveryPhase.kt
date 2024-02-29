package com.appmason.composeperformanceplayground.ui.screens.phases

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 * Compose has three phases - Composition, Layout, and Draw.
 * These are repeated in every frame where data is changed.
 * Then, you can skip some phase if data is not changed.
 * In the below example, recomposition occurs in every frame because of the color animation.
 * It would be better to skip composition and layout phases if only color is changed.
 *
 * Modifier.background(color) causes the compose to go through all the 3 phases.
 * Hence a lot of work is done if the color is animated.
 */
@Composable
fun CompositionInEveryPhase() {
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        var isNeedColorChange by remember { mutableStateOf(false) }
        val startColor = Color.Blue
        val endColor = Color.Green
        val backgroundColor by animateColorAsState(
            if (isNeedColorChange) endColor else startColor,
            animationSpec = tween(
                durationMillis = 800,
                delayMillis = 100,
                easing = LinearEasing
            ), label = "color animation"
        )
        LaunchedEffect(Unit) {
            while (true) {
                delay(1000)
                isNeedColorChange = !isNeedColorChange
            }
        }
        Box(
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.Center)
                .background(color = backgroundColor)
        )

        Text(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = "I have nothing to do with the Box"
        )
    }
}
