package com.appmason.composeperformanceplayground.ui.screens.lambdas

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appmason.composeperformanceplayground.ui.common.Article
import com.appmason.composeperformanceplayground.ui.common.ListViewModel

/**
 * Here on adding each item to the LazyList, the entire list (previous items) are recomposed.
 * This is because we have the clickable() modifier on each item of the list.
 * So the lambda onClick of each item is reallocated every time the LazyColumn recomposes.
 * Again this is because the object of the lambda is not remembered.
 *
 * See the solution in [StableClickableListScreen].
 */
@Composable
fun UnstableClickableListScreen(viewModel: ListViewModel = viewModel()) {
    val dynamicList by viewModel.dynamicArticles.collectAsState()
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            onClick = { viewModel.addArticleToDynamicList() }
        ) {
            Text(text = "Add item")
        }
        Spacer(modifier = Modifier.height(32.dp))
        UnstableClickableList(dynamicList)
    }
}

@Composable
private fun UnstableClickableList(
    articles: List<Article>, // List = Unstable, Article = Stable
    modifier: Modifier = Modifier // Stable
) {
    val context = LocalContext.current
    LazyColumn(modifier = modifier) {
        items(articles) { article ->
            Text(
                modifier = Modifier.clickable {
                    Toast.makeText(context, "Clicked on item: ${article.id}", Toast.LENGTH_SHORT).show()
                },
                text = article.name
            )
        }
    }
}