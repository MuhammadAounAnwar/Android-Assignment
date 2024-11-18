package com.android.goally.ui.copilot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.goally.ui.compose.components.FilterOption

@Composable
fun FiltersSection(filterOptions: List<FilterOption>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End)
    ) {
        filterOptions.forEach { filterOption ->
            FilterOption(
                leadingIconResId = filterOption.leadingIconResId,
                text = filterOption.text,
                onClick = filterOption.onClick
            )
        }
    }
}