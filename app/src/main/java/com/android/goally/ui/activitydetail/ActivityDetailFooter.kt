package com.android.goally.ui.activitydetail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.goally.R
import com.android.goally.data.model.api.response.copilot.Routines
import com.android.goally.data.model.api.response.copilot.getScheduleType
import com.android.goally.data.model.api.response.copilot.getTimeByScheduleType


data class FooterSection(val leadingIconResId: Int, val headingText: String, val bodyValues: List<String>?)

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
        routines.earlyStartMinutes?.let { "Limit Early Start: $it minutes" },
        routines.numberOfRunPerDay?.let { "Runs per day: $it" },
        routines.numberOfRunPerHour?.let { "Runs per hour: $it" }
    )

    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            FooterItem(R.drawable.fo_leading_icon, "Repeating", listOfNotNull(repeatingValue))
            FooterItem(R.drawable.fo_leading_icon, "Days", listOfNotNull(scheduleText))
        }

        FooterItem(R.drawable.fo_leading_icon, "Time", scheduleTime)
        FooterItem(R.drawable.fo_leading_icon, "Restrictions", restrictions)
    }


}

@Composable
fun FooterItem(leadingIconResId: Int, headingText: String, bodyValues: List<String>) {
    Column {
        FooterItemHeading(leadingIconResId, headingText)
        FooterItemBody(bodyValues)
    }
}

@Composable
fun FooterItemHeading(leadingIconResId: Int, headingText: String) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = leadingIconResId), contentDescription = null, modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = headingText, style = MaterialTheme.typography.bodyMedium
        )

    }
}

@Composable
fun FooterItemBody(bodyValues: List<String>) {
    bodyValues.forEach {
        Text(
            text = it, style = MaterialTheme.typography.bodyMedium
        )
    }
}

