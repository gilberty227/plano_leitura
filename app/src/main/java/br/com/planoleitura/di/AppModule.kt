package br.com.planoleitura.di

import android.app.Application
import androidx.room.Room
import br.com.planoleitura.data.data_source.AppDatabase
import br.com.planoleitura.data.repository.PlanRepositoryImpl
import br.com.planoleitura.domian.repository.PlanRepository
import br.com.planoleitura.domian.use_case.PlanUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun providePlanRepository(db: AppDatabase): PlanRepository{
        return PlanRepositoryImpl(db.planDao)
    }

    @Provides
    @Singleton
    fun providePlanUseCase(repository: PlanRepository): PlanUseCase{
        return PlanUseCase(repository)
    }
}