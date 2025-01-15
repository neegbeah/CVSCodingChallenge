package com.nr.cvsflickrcodechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nr.cvsflickrcodechallenge.data.Photo
import com.nr.cvsflickrcodechallenge.repository.PhotoRepo

/**
 * ViewModel for managing and providing data related to photos from the API.
 *
 * This class interacts with the [PhotoRepo] to fetch photos and exposes them as [LiveData] to the UI.
 * It also handles user input for searching photos based on tags.
 *
 * @property searchText A [LiveData] object representing the current text in the search bar.
 *                     The UI observes this property to update the search results when the text changes.
 * @property photos A [LiveData] object holding a list of [Photo] objects retrieved from the Flickr API.
 *                 The UI observes this property to display the photos.
 *
 * @author Neegbeah Reeves
 */

class PhotoViewModel: ViewModel() {
    //fetching photo data from the API.
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