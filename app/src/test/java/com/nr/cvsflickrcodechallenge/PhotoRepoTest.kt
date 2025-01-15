package com.nr.cvsflickrcodechallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nr.cvsflickrcodechallenge.data.Media
import com.nr.cvsflickrcodechallenge.data.Photo
import com.nr.cvsflickrcodechallenge.data.PhotoResponse
import com.nr.cvsflickrcodechallenge.repository.PhotoRepo
import com.nr.cvsflickrcodechallenge.retrofit.service.api.PhotoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

/**
 * Unit tests for the [PhotoRepo] class.
 *
 *
 * @author Neegbeah Reeves
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PhotoRepoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: PhotoRepo
    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    lateinit var apiService: PhotoApi

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        repository = PhotoRepo()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Test Case - Success should upload with Live Data`() = runTest {
        // Arrange
        val tags = "porcupine,forest"
        val mockResponse = Response.success(
            PhotoResponse(
                listOf(
                    Photo(
                        "title1",
                        "link1",
                        Media("url1")
                    )
                )
            )
        )

        whenever(apiService.getPhotos(anyString(), anyString(), anyInt()))
            .thenReturn(mockResponse)

        // Act
        repository.getPhotos(tags)

        // Advance time past all coroutines
        advanceUntilIdle()

        // Assert
        val photos = repository.photos.value
        assertNotNull(photos)
        assertEquals(2, photos?.size)
        assertEquals("title1", photos?.get(0)?.title)
        assertEquals("link1", photos?.get(0)?.media?.m)
    }

    @Test
    fun `Test Case - 404 Failure with Empty List`() = runTest {
        // Arrange
        val tags = "porcupine,forest"
        val mockResponse = Response.error<PhotoResponse>(
            404,
            ResponseBody.create(null, "")
        )

        whenever(apiService.getPhotos(anyString(), anyString(), anyInt()))
            .thenReturn(mockResponse)

        // Act
        repository.getPhotos(tags)

        // Advance time past all coroutines
        advanceUntilIdle()

        // Assert
        val photos = repository.photos.value
        assertNotNull(photos)
        assertTrue(photos?.isEmpty() == true)
    }

    @Test
    fun `Test Case - Get Photo Should return Success`() = runTest {
        // Arrange
        val photoId = "link1"
        val mockResponse = Response.success(
            PhotoResponse(
                listOf(
                    Photo(
                        "title1",
                        "link1",
                        Media("url1")
                    )
                )
            )
        )

        whenever(apiService.getPhotos(anyString(), anyString(), anyInt()))
            .thenReturn(mockResponse)

        // Act
        val result = repository.getPhotos(photoId)

        // Advance time past all coroutines
        advanceUntilIdle()

        // Assert
        assertNotNull(result)
        assertEquals("title1", result)
    }

    @Test
    fun `Test Case - Error Should Return Null`() = runTest {
        // Arrange
        val photoId = "invalid_id"
        val mockResponse = Response.error<PhotoResponse>(
            404,
            ResponseBody.create(null, "")
        )

        whenever(apiService.getPhotos(anyString(), anyString(), anyInt()))
            .thenReturn(mockResponse)

        // Act
        val result = repository.getPhotos(photoId)

        // Advance time past all coroutines
        advanceUntilIdle()

        // Assert
        assertEquals(null, result)
    }
}