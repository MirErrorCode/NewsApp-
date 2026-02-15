package com.mirlink.news.presentation.screens.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mirlink.news.presentation.components.NewsCard
import com.mirlink.news.presentation.components.SearchBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    onNewsClick: (String) -> Unit
) {

    val state by viewModel.uiState.collectAsState()

    val refreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = state.isLoading,
        onRefresh = { viewModel.refreshNews() },
        state = refreshState,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            SearchBar(
                query = state.searchQuery,
                onQueryChange = viewModel::onSearchQueryChange
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.news) { news ->
                    NewsCard(
                        news = news,
                        onClick = { onNewsClick(news.id) },
                        onFavoriteClick = {
                            viewModel.onToggleFavorite(news)
                        }
                    )
                }
            }
        }
    }
}
