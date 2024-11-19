package com.android.goally.ui.activitydetail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.android.goally.R
import com.android.goally.data.model.api.response.copilot.Activities
import com.android.goally.data.model.api.response.copilot.Routines

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RoutineDetailScreen(routines: Routines) {
    val activities = routines.activities

    Column(
        modifier = Modifier
            .fillMaxSize()
//            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            itemsIndexed(activities, key = { _, item -> item.Id }) { index, item ->
                ActivityItem(index, item)
            }
        }

        ActivityDetailScreenFooter(routines)
    }
}

@Composable
fun ActivityItem(index: Int, item: Activities) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = if (index % 2 == 0) Color.White else Color.LightGray.copy(alpha = 0.5f)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.imgURL)
                .crossfade(true)
                .placeholder(R.drawable.activity_placeholder)
                .error(R.drawable.activity_placeholder)
                .build(),
            contentDescription = item.imgURL,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp, start = 16.dp)
                .size(height = 100.dp, width = if (index % 2 == 0) 150.dp else 80.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.width(if (index % 2 == 0) 16.dp else 86.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}



