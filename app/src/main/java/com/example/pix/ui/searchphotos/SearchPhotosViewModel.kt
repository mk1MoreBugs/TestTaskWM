package com.example.pix.ui.searchphotos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pix.data.flickr.FlickrRepository
import com.example.pix.domain.entity.Picture
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchPhotosViewModel @Inject constructor(
    private val repository: FlickrRepository
): ViewModel() {
    init {
        addPhotosToUiState()
    }
    private val _listPhotos: MutableStateFlow<MutableList<Picture>> = MutableStateFlow(
        mutableListOf()
    )
    private val _pageCount: MutableStateFlow<Int> = MutableStateFlow(0)

    val uiState: StateFlow<SearchPhotosUiState> = combine(
        _listPhotos, _pageCount
    ) {  listPhotos, pageCount ->
        SearchPhotosUiState(
            listPhotos = listPhotos,
            pageCount = pageCount
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = SearchPhotosUiState()
    )

    fun addPhotosToUiState() {
        viewModelScope.launch {
            val photos = repository.searchPhotos()
            val newList = mutableListOf(uiState.value.listPhotos)
            newList.addAll(listOf(photos))
            _listPhotos.value.addAll(photos)
            _pageCount.value += 1
        }
    }
}

data class SearchPhotosUiState(
    val listPhotos: List<Picture> = listOf(),
    val pageCount: Int = 1
)