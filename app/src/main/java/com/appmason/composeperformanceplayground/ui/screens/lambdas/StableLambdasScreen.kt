package com.appmason.composeperformanceplayground.ui.screens.lambdas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appmason.composeperformanceplayground.ui.common.ListViewModel
import com.appmason.composeperformanceplayground.ui.common.NumberComposable

/**
 * Here the issue faced in [UnstableLambdasScreen] is fixed.
 *
 * Here we are wrapping the numberChanged call in a remember block. As the lambda would no longer be reallocated on recomposition,
 * thanks to the remember call, the inputs to the [NumberComposable] would be the same and so the composable would be skipped. This thing is
 * done automatically by the compiler when the view model is stable (say). Strong skipping mode expands this auto-remembering to lambdas with
 * unstable captures as well, which means every lambda in a composable function is now memoized.
 */
@Composable
fun StableLambdasScreen(viewModel: ListViewModel = viewModel()) { // ListViewModel is unstable
    val number by viewModel.number.collectAsState()
    var text by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        NumberComposable(
            current = number,
            onValueChange = remember { { viewModel.numberChanged(it) } }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = text, onValueChange = { text = it })
    }
}