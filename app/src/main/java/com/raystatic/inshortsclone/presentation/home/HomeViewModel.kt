package com.raystatic.inshortsclone.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.raystatic.domain.Resource
import com.raystatic.domain.model.News
import com.raystatic.domain.usecase.DeleteBookmarkedNewsByTitleUseCase
import com.raystatic.domain.usecase.GetNewsListUseCase
import com.raystatic.domain.usecase.InsertBookmarkedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase,
    private val insertBookmarkedNewsUseCase: InsertBookmarkedNewsUseCase,
    private val deleteBookmarkedNewsByTitleUseCase: DeleteBookmarkedNewsByTitleUseCase,
):ViewModel(){

    private val _news = MutableLiveData<PagingData<News>>()
    val news:LiveData<PagingData<News>> get() = _news

    fun getTrendingNews(country:String) = viewModelScope.launch {
        getNewsListUseCase.execute(country)
            .onEach {
                _news.value = it
            }
            .launchIn(viewModelScope)
    }

    fun addNewsToBookmark(news: News) = viewModelScope.launch {
        insertBookmarkedNewsUseCase.execute(news)
    }

    fun removeBookmark(title:String) = viewModelScope.launch {
        deleteBookmarkedNewsByTitleUseCase.execute(title)
    }

}