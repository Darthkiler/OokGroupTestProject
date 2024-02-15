package com.darthkiler.ookgrouptestproject.ui.screen.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.darthkiler.ookgrouptestproject.R
import com.darthkiler.ookgrouptestproject.domain.model.PostcardModel

@Composable
fun PostcardItem(
    modifier: Modifier = Modifier,
    item: PostcardModel
) {
    SubcomposeAsyncImage(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp)),
        model = item.imageUrl,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        error = {
            Box {
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.baseline_error_24),
                    contentDescription = null
                )
            }
        },
        loading = {
            Box {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(27.dp)
                        .align(Alignment.Center)
                )
            }
        }
    )
}
