package com.android.goally.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.goally.data.db.entities.RoutineEntity
import com.android.goally.data.model.api.response.copilot.Routines
import kotlinx.coroutines.flow.Flow


@Dao
interface CopilotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutines(routines: List<RoutineEntity>)

    @Query("SELECT * FROM routines")
    fun getRoutinesList(): Flow<List<RoutineEntity>>

}