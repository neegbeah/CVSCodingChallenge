package com.nr.cvsflickrcodechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.nr.cvsflickrcodechallenge.data.Photo
import com.nr.cvsflickrcodechallenge.ui.theme.CVSFlickrCodeChallengeTheme
import com.nr.cvsflickrcodechallenge.viewmodel.PhotoViewModel
import kotlinx.coroutines.launch

/**
 * Main activity for the Flickr image search application.
 *
 * This activity serves as the entry point for the app and is responsible for
 * setting up the user interface using Jetpack Compose. It utilizes a
 * [FlickrViewModel] to manage the data and state of the UI.
 *
 * @author Neegbeah Reeves
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CVSFlickrCodeChallengeTheme {
               PhotoApp()
            }
        }
    }
}



@Composable
fun PhotoApp() {
    val viewModel: PhotoViewModel = viewModel() // Get the view model instance
    val searchText by viewModel.searchText.observeAsState("")
    val photos by viewModel.photos.observeAsState(emptyList())
    val coroutineScope = rememberCoroutineScope()

    Column {
        SearchBar(searchText) { newText ->
            coroutineScope.launch {
                viewModel.onSearchTextChanged(newText)
            }
        }
        PhotoGrid(photos)
    }
}

/**
 * Composable function for the search bar.
 *
 *
 * This function provides a [TextField] for the user to input their search query.
 * Used TextField instead of Search bar for Simplicity
 * It updates the [PhotoViewModel] with the new search text whenever the user
 * modifies the input.
 *
 * @param searchText The current text in the search bar.
 * @param onSearchTextChanged A callback function that is invoked when the text
 *     in the search bar changes. It receives the new text as a parameter.
 */
@Composable
fun SearchBar(searchText: String, onSearchTextChanged: (String) -> Unit) {
    val coroutineScope = rememberCoroutineScope() // Create a CoroutineScope

    TextField(
        value = searchText,
        onValueChange = { newText ->
            coroutineScope.launch { // Launch a coroutine
                onSearchTextChanged(newText) // Now you can call the suspend function
            }
        },
        label = { Text("Search (e.g., porcupine, forest)") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}

/**
 * Composable function for displaying a grid of photos.
 *
 * This function displays a grid of images fetched from the Flickr API using
 * [LazyVerticalGrid]. Each image is represented by a [PhotoItem] composable.
 *
 * @param photos The list of [Photo] objects to display in the grid.
 */
@Composable
fun PhotoGrid(photos: List<Photo>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        items(photos) { photo ->
            PhotoItem(photo)
        }
    }
}

/**
 * Composable function for displaying a single photo item.
 *
 * This function displays a single image fetched from the Flickr API using
 * [AsyncImage] from the Coil library. The image is displayed within a [Card]
 * for visual styling.
 *
 * @param photo The [Photo] object representing the photo to display.
 */

@Composable
fun PhotoItem(photo: Photo) {
    Card(modifier = Modifier.padding(4.dp)) {
        // Use AsyncImage from Coil to load and display the image
        AsyncImage(
            model = photo.media?.m, // Image URL from the Flickr API
            contentDescription = photo.title,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview (name = "School Card", device = Devices.NEXUS_6P, showSystemUi = true)
@Composable
fun cardItemPreview(){
    PhotoApp()
}
