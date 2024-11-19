package com.android.goally.ui.copilot

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.goally.R


data class FilterOption(val leadingIconResId: Int, val text: String, val onClick: () -> Unit)

enum class FilterType {
    EMPTY,
    SCHEDULE,
    FOLDER
}

@Composable
fun FiltersSection(filterOptions: List<FilterOption>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.End)
    ) {
        filterOptions.forEach { filterOption ->
            FilterOptionItem(
                leadingIconResId = filterOption.leadingIconResId,
                text = filterOption.text,
                onClick = filterOption.onClick
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FilterOptionItem(
    leadingIconResId: Int = R.drawable.fo_leading_icon,
    trailingIconResId: Int = R.drawable.fo_trailing_icon,
    text: String = "Heading",
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        Icon(
            painter = painterResource(id = leadingIconResId),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.width(4.dp))

        Icon(
            painter = painterResource(id = trailingIconResId),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun FilterTagComponent(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp)
            .clickable { onClick.invoke() },
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End)
    ) {
        Text(text = "Filtered", color = MaterialTheme.colorScheme.error)
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Filter Icon",
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.error
        )
    }
}