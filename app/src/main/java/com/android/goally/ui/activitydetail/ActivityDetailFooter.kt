package com.android.goally.ui.activitydetail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.NotInterested
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.goally.R
import com.android.goally.data.model.api.response.copilot.Routines
import com.android.goally.data.model.api.response.copilot.getScheduleType
import com.android.goally.data.model.api.response.copilot.getTimeByScheduleType

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ActivityDetailScreenFooter(routines: Routines) {

    val repeatingValue = when {
        routines.type == "SCHEDULED" && routines.isScheduledByV2 -> routines.scheduleV2?.type ?: "Daily"
        routines.type == "SCHEDULED" -> "Daily"
        else -> routines.type
    }

    val scheduleText = when {
        routines.type == "SCHEDULED" && routines.isScheduledByV2 -> routines.scheduleV2?.getScheduleType() ?: "N/A"
        routines.type == "SCHEDULED" -> routines.schedule?.getScheduledDays() ?: "N/A"
        else -> "N/A"
    }

    val scheduleTime = when {
        routines.type == "SCHEDULED" && routines.isScheduledByV2 -> routines.scheduleV2?.getTimeByScheduleType() ?: listOf("N/A")
        routines.type == "SCHEDULED" -> routines.schedule?.getRepeatValue() ?: listOf("N/A")
        else -> listOf("N/A")
    }

    val restrictions = listOfNotNull(
        "Cancel Allowed: ${routines.allowClientToCancel}",
        "Snoozes: up to ${routines.numberOfSnoozeAllowed}",
        routines.earlyStartMinutes?.let { "Limit Early Start: $it min" },
        routines.numberOfRunPerDay?.let { "Runs per day: $it" },
        routines.numberOfRunPerHour?.let { "Runs per hour: $it" }
    )

    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            FooterItem(Icons.Default.Repeat, "Repeating", listOfNotNull(repeatingValue))
            FooterItem(Icons.Default.CalendarToday, "Days", listOfNotNull(scheduleText))
        }

        FooterItem(Icons.Default.AccessTime, "Time", scheduleTime)
        FooterItem(Icons.Default.NotInterested, "Restrictions", restrictions)
    }


}

@Composable
fun FooterItem(leadingIconResId: ImageVector, headingText: String, bodyValues: List<String>) {
    Column {
        FooterItemHeading(leadingIconResId, headingText)
        FooterItemBody(bodyValues)
    }
}

@Composable
fun FooterItemHeading(leadingIconResId: ImageVector, headingText: String) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = leadingIconResId,
            contentDescription = "Home Icon",
            tint = Color.Black,
            modifier = Modifier
                .size(16.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = headingText, style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp), color = Color.Black
        )

    }
}

@Composable
fun FooterItemBody(bodyValues: List<String>) {
    bodyValues.forEach {
        Text(
            text = it,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp), color = Color.Gray
        )
    }
}




