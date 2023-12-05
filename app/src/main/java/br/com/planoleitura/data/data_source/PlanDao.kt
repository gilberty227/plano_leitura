package br.com.planoleitura.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.planoleitura.domian.model.Plan
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao {

    @Query("SELECT * FROM `plan`")
    fun getPlans(): Flow<MutableList<Plan>>

    @Query("SELECT * FROM `plan` WHERE id = :id")
    suspend fun getPlanById(id: Int): Plan?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlan(plan: Plan)

    @Delete
    suspend fun deletePlan(plan: Plan)
}