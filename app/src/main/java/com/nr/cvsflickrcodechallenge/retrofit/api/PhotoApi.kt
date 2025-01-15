package com.nr.cvsflickrcodechallenge.retrofit.api

import com.nr.cvsflickrcodechallenge.data.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Defines the interface for interacting with the Flickr Photo API using Retrofit.
 *
 * This interface specifies the endpoints and parameters for retrieving public photos from Flickr.
 *
 * @author Neegbeah Reeves
 */

interface PhotoApi {
    /**
     * Retrieves a list of public photos from Flickr based on the specified tags.
     *
     * @param tags A comma-separated list of tags to filter the photos by.
     *             Defaults to "porcupine,forest" to search for photos related to porcupines and forests.
     * @param format The desired format of the response. Defaults to "json".
     * @param nojsoncallback A flag to disable JSON callback wrapping. Defaults to 1.
     * @return A [Response] object containing a [PhotoResponse] if the request is successful.
     *         The [Response] object provides access to the HTTP status code, headers, and the deserialized body.
     *         If the request fails, the [Response] object will contain error information.
     *
     * @throws IOException if a network error occurs during the API call.
     *
     * @GET("services/feeds/photos_public.gne") This annotation indicates that this is an HTTP GET request to the specified path.
     * @Query These annotations define query parameters to be included in the request URL.
     * suspend This keyword indicates that this is a suspending function, suitable for use in coroutines.
     */
    @GET("services/feeds/photos_public.gne")
    suspend fun getPhotos(
        @Query("tags") tags: String = "porcupine,forest",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1
    ): Response<PhotoResponse>
}