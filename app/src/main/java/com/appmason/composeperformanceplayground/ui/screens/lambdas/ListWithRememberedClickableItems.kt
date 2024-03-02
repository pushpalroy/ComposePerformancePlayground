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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appmason.composeperformanceplayground.ui.common.Article
import com.appmason.composeperformanceplayground.ui.common.ListViewModel

/**
 * Here the issue faced in [ListWithNonRememberedClickableItems] is fixed by wrapping the clickable in
 * a remember block. As the lambda object is remembered now, reallocation of the object does not
 * happen for all items of the LazyList.
 */
@Composable
fun ListWithRememberedClickableItems(viewModel: ListViewModel = viewModel()) {
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
        ListWithRememberedClickableItems(dynamicList)
    }
}

@Composable
private fun ListWithRememberedClickableItems(
    articles: List<Article>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    LazyColumn(modifier = modifier) {
        items(articles) { article ->
            Text(
                modifier = Modifier.then(
                    remember {
                        Modifier.clickable { // Remembered
                            Toast.makeText(context, "Clicked item: ${article.id}", Toast.LENGTH_SHORT).show()
                        }
                    }
                ),
                text = article.name
            )
        }
    }
}