package com.android.goally.ui.copilot

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.goally.R


data class FilterOption(val leadingIconResId: Int, val text: String, val onClick: () -> Unit)


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CopilotScreen(viewModel: CopilotViewModel = hiltViewModel()) {

    val scrollState = rememberLazyListState()
    val routines by viewModel.routines.collectAsState(initial = emptyList())

    val filterOptions = listOf(
        FilterOption(R.drawable.fo_leading_icon, "Schedule") {},
        FilterOption(R.drawable.fo_leading_icon, "Folder") {}
    )

    Column(modifier = Modifier.fillMaxSize()) {
        FiltersSection(filterOptions = filterOptions)

        LazyColumn(modifier = Modifier.fillMaxSize(), state = scrollState) {
            itemsIndexed(routines, key = { _, routine -> routine.Id }) { _, routine ->
                ScheduleItemRow(routine)
                Divider(modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }

}





