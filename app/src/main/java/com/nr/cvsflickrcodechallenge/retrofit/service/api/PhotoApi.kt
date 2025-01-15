package com.nr.cvsflickrcodechallenge.retrofit.service.api

import com.nr.cvsflickrcodechallenge.data.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    @GET("services/feeds/photos_public.gne")
    suspend fun getPhotos(
        @Query("tags") tags: String = "porcupine,forest",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1
    ): Response<PhotoResponse>
}