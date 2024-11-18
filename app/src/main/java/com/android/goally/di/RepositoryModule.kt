package com.android.goally.di

import com.android.goally.data.db.dao.*
import com.android.goally.data.network.rest.api.GeneralApi
import com.android.goally.data.repo.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGeneralRepo(
        authenticationApi: GeneralApi, authenticationDao: GeneralDao
    ): GeneralRepo {
        return GeneralRepo(
            authenticationApi,
            authenticationDao
        )
    }


    @Singleton
    @Provides
    fun provideCopilotRepo(
        authenticationApi: GeneralApi,
        copilotDao: CopilotDao
    ): CopilotRepo {
        return CopilotRepo(
            authenticationApi,
            copilotDao
        )
    }


}
