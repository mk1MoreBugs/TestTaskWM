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

    private val _listPhotos: MutableStateFlow<MutableList<Picture>> = MutableStateFlow(
        mutableListOf()
    )
    private val _pageCount: MutableStateFlow<Int> = MutableStateFlow(1)
    private val _searchTextField: MutableStateFlow<String> = MutableStateFlow("")

    val uiState: StateFlow<SearchPhotosUiState> = combine(
        _listPhotos, _pageCount, _searchTextField
    ) {  listPhotos, pageCount, searchTextField ->
        SearchPhotosUiState(
            listPhotos = listPhotos,
            pageCount = pageCount,
            searchTextField = searchTextField,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = SearchPhotosUiState()
    )

    init {
        addPhotosToUiState()
    }

    fun addPhotosToUiState() {
        viewModelScope.launch {
            val photos = repository.searchPhotos(
                text = uiState.value.searchTextField,
                page = uiState.value.pageCount,
                count = 50,
            )
            val newList = mutableListOf(uiState.value.listPhotos)
            newList.addAll(listOf(photos))
            _listPhotos.value.addAll(photos)
            _pageCount.value += 1
        }
    }

    fun makeNewSearchRequest() {
        _listPhotos.value.clear()
        _pageCount.value = 1
        addPhotosToUiState()
    }

    fun updateSearchTextField(value: String) {
        _searchTextField.value = value
    }
}

data class SearchPhotosUiState(
    val listPhotos: List<Picture> = listOf(),
    val pageCount: Int = 1,
    val searchTextField: String = "",
)