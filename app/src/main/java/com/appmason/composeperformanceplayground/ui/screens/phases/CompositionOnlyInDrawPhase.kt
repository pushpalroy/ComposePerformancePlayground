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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 * Modifier.drawBehind{} instead of Modifier.background(color) causes the compose to go through only the draw phase.
 * It is an important concept to defer reading state until you need it.
 * It makes functions to be executed again less frequently and skip Composition or Layout.
 */
@Composable
fun CompositionOnlyInDrawPhase() {
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
                .drawBehind {
                    drawRect(color = backgroundColor)
                }
        )

        Text(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = "I have nothing to do with the Box"
        )
    }
}
