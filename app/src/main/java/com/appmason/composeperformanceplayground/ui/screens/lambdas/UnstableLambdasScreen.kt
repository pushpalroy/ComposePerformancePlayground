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
 * Possibly one of the biggest misconceptions about Compose is the concept of “unstable lambdas” causing recomposition.
 * All lambdas in Compose are stable, so the concept of an unstable lambda is a bit of a misnomer.
 *
 * The developer of this composable expects that when the user types in the text field, NumberComposable will skip recomposition
 * because its inputs haven’t changed and the compiler report also confirms that it is skippable. But at runtime they see the layout inspector
 * showing this isn’t the case.
 *
 * The catch is that lambdas are just objects under the hood. When the [UnstableLambdasScreen] composable is recomposed, the onValueChange lambda
 * of [NumberComposable] is reallocated. When the [NumberComposable] is evaluated for skipping, compose runtime looks at each argument passed into
 * the composable and compares it to its previous value. The runtime sees that current is the same value but onValueChange has changed because it
 * has been reallocated and lambdas just use reference equality for their equals check (the object addresses are not the same), therefore the composable
 * is recomposed because its inputs have changed. The recomposition was caused by the lambda object changing, not the lambda being unstable.
 *
 * See the solution in [StableLambdasScreen].
 */
@Composable
fun UnstableLambdasScreen(viewModel: ListViewModel = viewModel()) { // ListViewModel is unstable
    val number by viewModel.number.collectAsState()
    var text by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        NumberComposable(
            current = number,
            onValueChange = { viewModel.numberChanged(it) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = text, onValueChange = { text = it })
    }
}