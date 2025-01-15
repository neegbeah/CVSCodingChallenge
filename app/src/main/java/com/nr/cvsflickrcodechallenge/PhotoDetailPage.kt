package com.nr.cvsflickrcodechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.nr.cvsflickrcodechallenge.data.Photo

class PhotoDetailPage: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            PhotoDetailPage(
//                PhotoMetadata(
//                    "https://fastly.picsum.photos/id/237/200/300.jpg?hmac=TmmQSbSh2oF9qC5pqNq_t8xZg1hPy2vrv2x9qW_t_cE",
//                    "Example Name",
//                    "October 26, 2023",
//                    Media("")
//                )
//            )
        }
    }
}

@Composable
fun PhotoDetailPage(details: Photo) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Photo (using Coil for image loading)
        Image(
            painter = rememberAsyncImagePainter(model = details.media?.m),
            contentDescription = "Detail Photo",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop // or ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Name
        details?.title?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Date
        details?.dateTaken?.let {
            Text(
                text = it,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
//    PhotoDetailPage(PhotoMetadata("https://live.staticflickr.com/65535/54093478871_3472926316_m.jpg","Preview Name", "Preview Date", (Media(""))))
}