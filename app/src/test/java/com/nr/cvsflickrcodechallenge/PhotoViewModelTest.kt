package com.nr.cvsflickrcodechallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nr.cvsflickrcodechallenge.data.Media
import com.nr.cvsflickrcodechallenge.data.Photo
import com.nr.cvsflickrcodechallenge.repository.PhotoRepo
import com.nr.cvsflickrcodechallenge.viewmodel.PhotoViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PhotoViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: PhotoRepo

    private lateinit var viewModel: PhotoViewModel
    private val testDispatcher = UnconfinedTestDispatcher()


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = PhotoViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Test Case - considering searchPhotos calling repository getPhotos`() = runTest {
        // Arrange
        val tags = "porcupine,forest"

        // Act
        viewModel.searchPhotos(tags)

        // Advance time past all coroutines
        advanceUntilIdle()

        // Assert
        verify(repository).getPhotos(tags)
    }
}