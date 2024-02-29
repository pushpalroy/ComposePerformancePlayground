package com.appmason.composeperformanceplayground.ui.common

import androidx.lifecycle.ViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ListViewModel : ViewModel() {
    val articles = persistentListOf(
        Article(1, "Article 1"),
        Article(2, "Article 2"),
        Article(3, "Article 3"),
        Article(4, "Article 4"),
        Article(5, "Article 5"),
        Article(6, "Article 6"),
        Article(7, "Article 7"),
        Article(8, "Article 8"),
        Article(9, "Article 9"),
        Article(10, "Article 10")
    )

    private val _number = MutableStateFlow("")
    val number: StateFlow<String> = _number.asStateFlow()

    fun numberChanged(newNumber: String) {
        _number.update { newNumber }
    }
}

// Stable
data class Article(
    val id: Int,
    val name: String,
)