package com.nr.cvsflickrcodechallenge.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.nr.cvsflickrcodechallenge.ui.theme.CVSFlickrCodeChallengeTheme

/**
 * An Activity that displays the details of a selected photo.
 *
 * This activity receives data (image URL, title, date, author, description) via Intent
 * and displays the photo along with its details in the layout.
 *
 * @author Neegbeah Reeves
 */

class PhotoDetailPage : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CVSFlickrCodeChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    getContentDataFromIntent()
                }

            }
        }
    }

    /**
     * Extracts the photo data from the intent and passes it to the [PhotoDetailPage] composable.
     *
     * This function retrieves the image URL, title, date, author, and description from the intent
     * extras and then calls the [PhotoDetailPage] composable function to display.
     */
    @SuppressLint("ComposableNaming")
    @Composable
    private fun getContentDataFromIntent() {
        val imageUrl = intent.getStringExtra("imageUrl")
        val imageTitle = intent.getStringExtra("imageTitle")
        val imageDate = intent.getStringExtra("imageDate")
        val imageAuthor = intent.getStringExtra("imageAuthor")
        val imageDescription = intent.getStringExtra("imageDesc")

        PhotoDetailPage(imageUrl, imageTitle, imageDate, imageAuthor, imageDescription)

    }

    /**
     * Composable function that displays the details of a photo.
     *
     * @param imageUrl The URL of the photo.
     * @param imageTitle The title of the photo.
     * @param imageDate The date the photo was taken.
     * @param imageAuthor The author of the photo.
     * @param imageDescription The description of the photo.
     */
    @SuppressLint("NotConstructor")
    @Composable
    fun PhotoDetailPage(
        imageUrl: String?,
        imageTitle: String?,
        imageDate: String?,
        imageAuthor: String?,
        imageDescription: String?
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Photo (using Coil for image loading)
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Detail Photo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop // or ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))
            // Name
            imageTitle?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Date
            imageDate?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            // Author
            imageAuthor?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            // Author
            imageDescription?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 16.sp
                )
            }
        }
    }
}
