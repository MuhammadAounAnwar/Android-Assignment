package com.android.goally.data.repo

import com.android.goally.data.db.dao.CopilotDao
import com.android.goally.data.db.entities.toEntityList
import com.android.goally.data.model.api.response.copilot.Routines
import com.android.goally.data.network.rest.api.GeneralApi


class CopilotRepo(
    private val generalApi: GeneralApi,
    private val copilotDao: CopilotDao,
) {
    suspend fun insertRoutines(routines: List<Routines>) {
        val routineEntities = routines.toEntityList()
        copilotDao.insertRoutines(routineEntities)
    }

    fun getRoutines() = copilotDao.getRoutinesList()

    suspend fun getCopilotList() = generalApi.getCopilotList()
}