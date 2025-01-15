package com.nr.cvsflickrcodechallenge.data


data class PhotoResponse(val photos: List<Photo>)
data class Photo(val title: String?, val dateTaken: String?, val media: Media?)
data class Media(val m: String?) // Where the actual source JPG will be at.