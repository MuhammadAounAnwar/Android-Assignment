package com.android.goally.ui.copilot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.goally.data.db.entities.toDomain
import com.android.goally.data.db.entities.toDomainList
import com.android.goally.data.model.api.response.copilot.Routines
import com.android.goally.data.repo.CopilotRepo
import com.android.goally.util.LogUtil
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CopilotViewModel @Inject constructor(
    private val copilotRepo: CopilotRepo
) : ViewModel() {

    private val _routines = MutableStateFlow<List<Routines>>(emptyList())
    val routines: StateFlow<List<Routines>> get() = _routines

    init {
        getCopilotListFromServer()
        loadRoutines()
    }

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


    fun loadRoutines() {
        viewModelScope.launch {
            copilotRepo.getRoutines().collectLatest { routineEntities ->
                val routines = routineEntities.toDomainList()
                _routines.value = routines
            }
        }
    }

}