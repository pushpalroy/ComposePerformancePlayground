package com.appmason.composeperformanceplayground.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onToggle: () -> Unit
) {
    Switch(
        checked = isFavorite,
        onCheckedChange = { onToggle() }
    )
}

@Composable
fun NumberComposable(
    current: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text("Value $current")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = current, onValueChange = { onValueChange(it) })
    }
}