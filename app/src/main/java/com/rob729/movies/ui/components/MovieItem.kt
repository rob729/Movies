package com.rob729.movies.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rob729.movies.utils.Utils
import com.rob729.movies.models.ui.MovieUiModel

@Composable
fun MovieItem(movieUiModel: MovieUiModel, onClick: () -> Unit) {

    Column(modifier = Modifier.clickable(onClick = onClick)) {
        AsyncImage(
            model = Utils.getImageRequestModel(
                LocalContext.current,
                movieUiModel.posterUrl
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp)),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = movieUiModel.title, fontSize = 16.sp)
    }
}