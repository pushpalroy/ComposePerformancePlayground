package com.appmason.composeperformanceplayground.ui.common

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
        Article(10, "Article 10"),
        Article(11, "Article 11"),
        Article(12, "Article 12"),
        Article(13, "Article 13"),
        Article(14, "Article 14"),
        Article(15, "Article 15"),
        Article(16, "Article 16"),
        Article(17, "Article 17"),
        Article(18, "Article 18"),
        Article(19, "Article 19"),
        Article(20, "Article 20")
    )

    private val _dynamicArticles = MutableStateFlow<List<Article>>(emptyList())
    val dynamicArticles = _dynamicArticles.asStateFlow()

    private val _number = MutableStateFlow("")
    val number: StateFlow<String> = _number.asStateFlow()

    init {
        viewModelScope.launch {
            _dynamicArticles.value = articles.subList(0, 10)
        }
    }

    fun addArticleToDynamicList() {
        viewModelScope.launch {
            _dynamicArticles.value = _dynamicArticles.value + articles[_dynamicArticles.value.size]
        }
    }

    fun numberChanged(newNumber: String) {
        viewModelScope.launch {
            _number.update { newNumber }
        }
    }
}

// Stable
data class Article(
    val id: Int,
    val name: String,
)