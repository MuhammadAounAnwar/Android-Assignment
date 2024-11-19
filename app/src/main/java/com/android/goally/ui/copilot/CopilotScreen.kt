package com.android.goally.ui.copilot

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.DrawerValue
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.goally.R
import com.android.goally.ui.RightSideDrawer
import com.android.goally.ui.compose.NoInternetDialog
import com.android.goally.ui.compose.navigateToRoutineDetail
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CopilotScreen(viewModel: CopilotViewModel = hiltViewModel(), navController: NavController? = null) {

    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val isConnected by viewModel.isConnected.collectAsState()

    var showDialog by remember { mutableStateOf(!isConnected) }

    LaunchedEffect(isConnected) {
        showDialog = !isConnected
    }

    val recentlySelectedFilter by viewModel.recentlySelectedFilter.collectAsState()
    val selectedItem by viewModel.selectedItem.collectAsState()
    val routines by viewModel.routinesList.collectAsState(initial = emptyList())
    val filterResults by viewModel.results.collectAsState(initial = emptyMap())


    val filterOptions = listOf(
        FilterOption(R.drawable.fo_leading_icon, "Schedule") {
            viewModel.setRecentlySelectedFilter(FilterType.SCHEDULE)
            scope.launch {
                drawerState.open()
            }
        },
        FilterOption(R.drawable.fo_folder_icon, "Folder") {
            viewModel.setRecentlySelectedFilter(FilterType.FOLDER)
            scope.launch {
                drawerState.open()
            }
        }
    )

    RightSideDrawer(
        heading = recentlySelectedFilter.name,
        items = filterResults,
        selectedItem = selectedItem,
        onItemSelected = {
            viewModel.setSelectedItem(it)
            scope.launch { drawerState.close() }
        },
        drawerState = drawerState,
        content = {

            NoInternetDialog(
                isVisible = showDialog,
                onDismiss = { showDialog = false }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                FiltersSection(filterOptions = filterOptions)

                TagComponent(
                    isInternetConnected = !isConnected,
                    isFilterSelected = recentlySelectedFilter != FilterType.EMPTY && selectedItem != null
                ) {
                    viewModel.setRecentlySelectedFilter(FilterType.EMPTY)
                    viewModel.setSelectedItem(null)
                }

                LazyColumn(modifier = Modifier.fillMaxSize(), state = scrollState) {
                    itemsIndexed(routines, key = { _, routine -> routine.Id }) { _, routine ->
                        ScheduleItemRow(routine, onClickListener = {
                            navController?.navigateToRoutineDetail(routine.Id)
                        })
                        Divider(modifier = Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    )

}






