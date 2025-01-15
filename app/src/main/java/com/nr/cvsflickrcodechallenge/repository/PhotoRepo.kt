package com.nr.cvsflickrcodechallenge.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nr.cvsflickrcodechallenge.data.Photo
import com.nr.cvsflickrcodechallenge.retrofit.client.PhotoClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Repository class responsible for fetching and managing photo data from the Flickr API.
 *
 * This class abstracts the data fetching logic from [PhotoViewModel] and provides a
 * [LiveData] stream of [Photo] objects to observe. It uses [PhotoClient] to make network
 * requests to the Flickr API.
 *
 * @author Neegbeah Reeves
 */
class PhotoRepo {

    private val _photos = MutableLiveData<List<Photo>>(emptyList())
    val photos: LiveData<List<Photo>> = _photos

    // Retrieve the photos stored in the repo by using a CoroutineScope. No doable by lifecycle. Will
    // fresh the data from the IO from network call, and then store data from the Main Thread without interruptions.
    fun getPhotos(tags: String = "porcupine,forest") {
        CoroutineScope(Dispatchers.IO).launch {
            val response = PhotoClient.photoApiInstance.getPhotos(tags = tags)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _photos.value = response.body()?.items ?: emptyList()
                } else {
                    // TODO: Handle error - Will come back if there is time
                }
            }
        }
    }
}
