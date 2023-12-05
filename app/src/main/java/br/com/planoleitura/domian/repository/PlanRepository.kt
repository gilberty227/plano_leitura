package br.com.planoleitura.domian.repository

import br.com.planoleitura.domian.model.Plan
import kotlinx.coroutines.flow.Flow

interface PlanRepository {

    fun getPlans(): Flow<MutableList<Plan>>

    suspend fun getNoteById(id: Int): Plan?
    suspend fun insertPlan(plan: Plan)
    suspend fun deletePlan(plan: Plan)
}