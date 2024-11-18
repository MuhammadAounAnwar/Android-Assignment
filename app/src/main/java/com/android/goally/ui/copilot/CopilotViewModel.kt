package com.android.goally.ui.copilot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.goally.data.db.entities.toDomainList
import com.android.goally.data.model.api.response.copilot.Routines
import com.android.goally.data.repo.CopilotRepo
import com.android.goally.util.LogUtil
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CopilotViewModel @Inject constructor(
    private val copilotRepo: CopilotRepo
) : ViewModel() {

    private var _routines = MutableStateFlow<List<Routines>>(emptyList())
    val routines: StateFlow<List<Routines>> get() = _routines

    private var _recentlySelectedFilter = MutableStateFlow(FilterType.EMPTY)
    val recentlySelectedFilter: StateFlow<FilterType> = _recentlySelectedFilter.asStateFlow()

    private var _selectedItem = MutableStateFlow<Map.Entry<String, Int>?>(null)
    val selectedItem: StateFlow<Map.Entry<String, Int>?> = _selectedItem.asStateFlow()

    val results: Flow<Map<String, Int>> =
        combine(recentlySelectedFilter, routines) { selectedFilter, routineList ->
            val groupedResults = when (selectedFilter) {
                FilterType.FOLDER -> routineList.groupingBy { it.folder }.eachCount()
                FilterType.SCHEDULE -> {
                    routineList.filter {
                        it.type == "SCHEDULED"
                    }.run {
                        this.groupingBy { it.scheduleV2?.type.orEmpty() }.eachCount()
                    }
                }

                else -> emptyMap()
            }

            val allCount = routineList.size
            val updatedResults = groupedResults.toMutableMap()

            if (groupedResults.isNotEmpty()) {
                updatedResults["All"] = allCount
            }

            updatedResults.toSortedMap()
        }.distinctUntilChanged()


    val routinesList: Flow<List<Routines>> = combine(recentlySelectedFilter, selectedItem, routines) { selectedFilter, item, routineList ->
        when {
            item?.key == "All" -> routineList
            selectedFilter == FilterType.FOLDER && item != null -> routineList.filter { it.folder == item.key }
            selectedFilter == FilterType.SCHEDULE && item != null -> routineList.filter { it.scheduleV2?.type == item.key }
            else -> routineList
        }
    }.distinctUntilChanged()


    init {
        getCopilotListFromServer()
        loadRoutines()
    }

    fun setRecentlySelectedFilter(filterType: FilterType) =
        _recentlySelectedFilter.tryEmit(filterType)

    fun setSelectedItem(item: Map.Entry<String, Int>?) = _selectedItem.tryEmit(item)

    private fun getCopilotListFromServer(
        onLoading: (Boolean) -> Unit = {},
        onError: (String) -> Unit = {},
        onSuccess: (String) -> Unit = {}
    ) {
        onLoading(true)
        viewModelScope.launch {
            when (val res = copilotRepo.getCopilotList()) {
                is NetworkResponse.Success -> {
                    LogUtil.i(res.body.toString())
                    if (res.body.routines.isNullOrEmpty()) {
                        onError("Server is down")
                    } else {
                        onSuccess("Server is up")

                        res.body.routines?.let {
                            copilotRepo.insertRoutines(it)
                        }
                    }
                    onLoading(false)
                }

                is NetworkResponse.ServerError -> {
                    LogUtil.e(res.code.toString())
                    LogUtil.e(res.body?.message)
                    onError(res.body?.message ?: "Server error")
                    onLoading(false)
                }

                is NetworkResponse.NetworkError -> {
                    res.error.printStackTrace()
                    onError(res.error.message ?: "Network error")
                    onLoading(false)
                }

                is NetworkResponse.UnknownError -> {
                    res.error.printStackTrace()
                    onError(res.error.message ?: "Unknown error")
                    onLoading(false)
                }
            }
        }
    }

    private fun loadRoutines() {
        viewModelScope.launch {
            copilotRepo.getRoutines().collectLatest { routineEntities ->
                val routines = routineEntities.toDomainList()
                _routines.value = routines
            }
        }
    }

    fun getRoutineById(id: String) = routines.value.first { it.Id == id }

}