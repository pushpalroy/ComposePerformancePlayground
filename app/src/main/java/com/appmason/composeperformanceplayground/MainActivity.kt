package com.appmason.composeperformanceplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.appmason.composeperformanceplayground.ui.screens.list.UnstableListScreen
import com.appmason.composeperformanceplayground.ui.screens.transformAnimation.TransformUsingGraphicsLayerModifier
import com.appmason.composeperformanceplayground.ui.screens.transformAnimation.TransformUsingRotateModifier
import com.appmason.composeperformanceplayground.ui.theme.ComposePerformancePlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePerformancePlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    /**
                     * Uncomment one screen at a time to run each example.
                     * Use the Layout Inspector to see recomposition counts.
                     */
                    UnstableListScreen()
                    // StableListScreen()
                    // UnstableClickableListScreen()
                    // StableClickableListScreen()
                    // UnstableLambdasScreen()
                    // StableLambdasScreen()
                    // CompositionInEveryPhase()
                    // CompositionOnlyInDrawPhase()
                    // UnstableClickableScreen()
                    // StableClickableScreen()
                    // TransformUsingRotateModifier()
                    // TransformUsingGraphicsLayerModifier()
                }
            }
        }
    }
}