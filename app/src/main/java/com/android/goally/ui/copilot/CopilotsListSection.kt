package com.android.goally.ui.copilot


import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.goally.R
import com.android.goally.data.model.api.response.copilot.Routines
import com.android.goally.data.model.api.response.copilot.getScheduleType
import com.android.goally.data.model.api.response.copilot.getScheduledDays


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleItemRow(item: Routines) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ScheduleItemImage(item.imgURL)
        if (isPortrait) ScheduleItemText(item) else ScheduleItemTextForLandscape(item)
    }
}

@Composable
fun ScheduleItemImage(imgURL: String?) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imgURL)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.app_logo),
        contentDescription = imgURL,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .size(80.dp)
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleItemText(item: Routines) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2
        )

        val scheduleText = when {
            item.isScheduledByV2 -> item.scheduleV2?.getScheduleType()
            else -> item.schedule?.getScheduledDays()
        }

        Text(
            text = scheduleText ?: "Not Scheduled",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Text(
            text = item.category,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleItemTextForLandscape(item: Routines) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2
        )

        val scheduleText = when {
            item.isScheduledByV2 -> item.scheduleV2?.getScheduleType()
            else -> item.schedule?.getScheduledDays()
        }

        Text(
            text = scheduleText ?: "Not Scheduled",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Text(
            text = item.category,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}



