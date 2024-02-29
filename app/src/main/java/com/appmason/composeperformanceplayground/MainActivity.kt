package com.appmason.composeperformanceplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.appmason.composeperformanceplayground.ui.screens.phases.CompositionInEveryPhase
import com.appmason.composeperformanceplayground.ui.screens.phases.CompositionOnlyInDrawPhase
import com.appmason.composeperformanceplayground.ui.theme.ComposePerformancePlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePerformancePlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    /**
                     * With strong skipping enabled:
                     * Composables with unstable parameters can be skipped.
                     * All lambdas in composable functions are now remembered for you.
                     */
                    // UnstableListScreen()
                    // StableListScreen()
                    // UnstableLambdasScreen()
                    // StableLambdasScreen()
                    // CompositionInEveryPhase()
                    CompositionOnlyInDrawPhase()
                }
            }
        }
    }
}