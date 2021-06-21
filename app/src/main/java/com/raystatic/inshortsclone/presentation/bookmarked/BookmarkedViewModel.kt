package com.raystatic.inshortsclone.presentation.bookmarked

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raystatic.domain.Resource
import com.raystatic.domain.model.News
import com.raystatic.domain.usecase.DeleteAllBookmarkedNewsUseCase
import com.raystatic.domain.usecase.DeleteBookmarkedNewsByTitleUseCase
import com.raystatic.domain.usecase.GetBookmarkedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookmarkedViewModel @Inject constructor(
    private val getBookmarkedNewsUseCase: GetBookmarkedNewsUseCase,
    private val deleteBookmarkedNewsByTitleUseCase: DeleteBookmarkedNewsByTitleUseCase,
    private val deleteAllBookmarkedNewsUseCase: DeleteAllBookmarkedNewsUseCase
):ViewModel() {

    private val _bookmarkedNews = MutableLiveData<List<News>>()
    val bookmarkedNews: LiveData<List<News>> get() = _bookmarkedNews

    fun getBookmarkedNews() = viewModelScope.launch {
        getBookmarkedNewsUseCase.execute()
            .onEach {
                _bookmarkedNews.value = it
            }
            .launchIn(viewModelScope)
    }

    fun removeBookmark(title:String) = viewModelScope.launch {
        deleteBookmarkedNewsByTitleUseCase.execute(title)
    }

    fun clearBookmarks() = viewModelScope.launch {
        deleteAllBookmarkedNewsUseCase.execute()
    }

}