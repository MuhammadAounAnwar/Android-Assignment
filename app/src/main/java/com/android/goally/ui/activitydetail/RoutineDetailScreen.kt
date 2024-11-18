package com.android.goally.ui.activitydetail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(activities, key = { _, item -> item.Id }) { index, item ->
                ActivityItem(index, item)
            }
        }

//        ActivityDetailScreenFooter(routines)
    }
}


@Composable
fun ActivityItem(index: Int, item: Activities) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(item.imgURL).crossfade(true).build(),
            placeholder = painterResource(R.drawable.app_logo),
            contentDescription = item.imgURL,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(if (index % 2 == 0) 64.dp else 48.dp)
        )

        Column {
            Text(
                text = item.name, style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            /*Text(
                text = item.number.toString(),
                style = MaterialTheme.typography.bodyMedium
            )*/
        }
    }
}