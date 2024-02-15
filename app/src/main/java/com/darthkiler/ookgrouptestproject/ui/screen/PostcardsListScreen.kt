package com.darthkiler.ookgrouptestproject.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.darthkiler.ookgrouptestproject.ui.screen.items.ErrorMessage
import com.darthkiler.ookgrouptestproject.ui.screen.items.LoadingNextPageItem
import com.darthkiler.ookgrouptestproject.ui.screen.items.PageLoader
import com.darthkiler.ookgrouptestproject.ui.screen.items.PostcardItem

@Composable
fun PostcardsListScreen(
    viewModel: PostcardsListScreenViewModel = viewModel()
) {
    val resultsPagingItems = viewModel.postcardsListState.collectAsLazyPagingItems()

    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Fixed(count = 2),
    ) {
        items(resultsPagingItems.itemCount) { index ->
            resultsPagingItems[index]?.let {
                PostcardItem(
                    modifier = Modifier.padding(all = 8.dp),
                    item = it
                )
            }
        }

        resultsPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item(
                        span = {
                            GridItemSpan(2)
                        }
                    ) {
                        PageLoader(
                            modifier = Modifier
                        )
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = resultsPagingItems.loadState.refresh as LoadState.Error
                    item(
                        span = {
                            GridItemSpan(2)
                        }
                    ) {
                        ErrorMessage(
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item(
                        span = {
                            GridItemSpan(2)
                        }
                    ) { LoadingNextPageItem() }
                }

                loadState.append is LoadState.Error -> {
                    val error = resultsPagingItems.loadState.append as LoadState.Error
                    item(
                        span = {
                            GridItemSpan(2)
                        }
                    ) {
                        ErrorMessage(
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }
            }
        }
    }
}
