package br.com.planoleitura.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.planoleitura.domian.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM user")
    fun getPlans(): Flow<MutableList<User>>

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getPlanById(id: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlan(user: User)

    @Delete
    suspend fun deletePlan(user: User)
}