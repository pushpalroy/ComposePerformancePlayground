package com.appmason.composeperformanceplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.appmason.composeperformanceplayground.ui.screens.lambdas.StableClickableListScreen
import com.appmason.composeperformanceplayground.ui.screens.lambdas.UnstableClickableListScreen
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
                     */
                    // UnstableListScreen()
                    // StableListScreen()
                    // UnstableClickableListScreen()
                    StableClickableListScreen()
                    // UnstableLambdasScreen()
                    // StableLambdasScreen()
                    // CompositionInEveryPhase()
                    // CompositionOnlyInDrawPhase()
                    // UnstableClickableScreen()
                    // StableClickableScreen()
                }
            }
        }
    }
}