package com.nr.cvsflickrcodechallenge.retrofit.client

import com.nr.cvsflickrcodechallenge.retrofit.api.PhotoApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Client object as a singleton responsible for creating and managing the Retrofit instance for the Flickr Photo API.
 *
 * This object provides a centralized way to access the [PhotoApi] interface, ensuring that only one instance
 * of the Retrofit client and its associated dependencies are created and used throughout the application.
 *
 * Using an `object` declaration makes `PhotoClient` a singleton, meaning there will only ever be a single
 * instance of it. This is useful for managing resources like network clients that are expensive to create
 * and should be reused.
 *
 * The `object` approach is preferred here over an `interface` because we are dealing with concrete
 * implementation details (creating and configuring the Retrofit instance), which is not the responsibility
 * of an interface. Interfaces are meant to define contracts, not provide implementations.
 *
 * @property photoApiInstance The lazily initialized instance of the [PhotoApi] interface. This instance
 *                           is created using Retrofit and is ready to make API calls. The `lazy`
 *                           delegate ensures that the instance is created only when it's first accessed,
 *                           improving startup performance.
 *
 * @property loggingInterceptor
 *
 *
 */

object PhotoClient {
    private const val BASE_URL = "https://api.flickr.com"

    // Using Interceptor to Debug Response. Can use debugger but better to see it in logcat
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val photoApiInstance: PhotoApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PhotoApi::class.java)
    }
}