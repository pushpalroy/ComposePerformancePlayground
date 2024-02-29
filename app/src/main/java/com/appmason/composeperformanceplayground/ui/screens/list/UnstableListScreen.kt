package com.appmason.composeperformanceplayground.ui.screens.list

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appmason.composeperformanceplayground.ui.common.Article
import com.appmason.composeperformanceplayground.ui.common.FavoriteButton
import com.appmason.composeperformanceplayground.ui.common.ListViewModel

/**
 * Without strong skipping mode enabled, when the FavoriteButton was toggled, the list of articles
 * would also be recomposed as it has an unstable parameter type (List). With strong skipping enabled,
 * ArticleList would be skipped as the list instance (articles), has not changed.
 */
@Composable
fun UnstableListScreen(viewModel: ListViewModel = viewModel()) {
    var favorite by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        FavoriteButton(isFavorite = favorite, onToggle = { favorite = !favorite })
        Spacer(modifier = Modifier.height(16.dp))
        UnstableList(viewModel.articles)
    }
}

@Composable
fun UnstableList(
    articles: List<Article>, // List = Unstable, Article = Stable
    modifier: Modifier = Modifier // Stable
) {
    LazyColumn(modifier = modifier) {
        items(articles) { article ->
            Text(text = article.name)
        }
    }
}