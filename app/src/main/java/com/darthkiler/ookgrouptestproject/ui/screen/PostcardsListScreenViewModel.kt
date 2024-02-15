package com.darthkiler.ookgrouptestproject.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.darthkiler.ookgrouptestproject.domain.model.PostcardModel
import com.darthkiler.ookgrouptestproject.domain.usecase.GetPostcardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PostcardsListScreenViewModel @Inject constructor(
    private val getPostcardsUseCase: GetPostcardsUseCase
) : ViewModel() {

    private val _postcardsListState = MutableStateFlow(PagingData.empty<PostcardModel>())
    val postcardsListState get() = _postcardsListState

    init {
        getPostcardsUseCase()
            .onEach { data ->
                _postcardsListState.update { data }
            }.launchIn(viewModelScope)
    }
}
