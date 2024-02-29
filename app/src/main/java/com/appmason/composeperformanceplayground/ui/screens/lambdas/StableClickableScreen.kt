package com.appmason.composeperformanceplayground.ui.screens.lambdas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * To fix the issue in [UnstableClickableScreen], the onClick lambda is wrapped
 * in a remember block thus reallocation of this object does not happen everytime.
 */
@Composable
fun StableClickableScreen() {
    var text by remember { mutableStateOf("") }
    var isClicked by remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            modifier = Modifier,
            text = "Toggle state: $isClicked"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .padding(8.dp)
                .then(
                    remember {
                        Modifier.clickable { isClicked = !isClicked }
                    }
                ),
            text = "Toggle me"
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = text, onValueChange = { text = it })
    }
}