package br.com.planoleitura.data.repository

import br.com.planoleitura.data.data_source.PlanDao
import br.com.planoleitura.domian.model.Plan
import br.com.planoleitura.domian.repository.PlanRepository
import kotlinx.coroutines.flow.Flow

class PlanRepositoryImpl(private val dao: PlanDao) : PlanRepository {
    override fun getPlans(): Flow<MutableList<Plan>> {
        return dao.getPlans()
    }

    override suspend fun getNoteById(id: Int): Plan? {
        return dao.getPlanById(id)
    }

    override suspend fun insertPlan(plan: Plan) {
        dao.insertPlan(plan)
    }

    override suspend fun deletePlan(plan: Plan) {
        dao.deletePlan(plan)
    }

}