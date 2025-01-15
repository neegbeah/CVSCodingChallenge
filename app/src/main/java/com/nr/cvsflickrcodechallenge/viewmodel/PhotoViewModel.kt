package com.nr.cvsflickrcodechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nr.cvsflickrcodechallenge.data.Photo
import com.nr.cvsflickrcodechallenge.repository.PhotoRepo

class PhotoViewModel: ViewModel() {
    private val repository: PhotoRepo = PhotoRepo()

    private val _searchText = MutableLiveData("")
    val searchText: LiveData<String> = _searchText

    val photos: LiveData<List<Photo>> = repository.photos

    init {
        searchPhotos()
    }

    // ViewModel Method to check from the UI if the search change.
    fun onSearchTextChanged(text: String) {
        _searchText.value = text
        searchPhotos(text)
    }

    // Just the default when it loads up initally. Type in anything from the search bar to retrieve it
    fun searchPhotos(tags: String = "porcupine,forest") {
        repository.getPhotos(tags)
    }
}