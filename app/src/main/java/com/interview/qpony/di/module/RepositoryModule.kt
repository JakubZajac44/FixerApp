package com.interview.qpony.di.module

import com.interview.qpony.repository.FixerRepository
import com.interview.qpony.service.FixerApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideFixerRepository(fixerApi: FixerApi) : FixerRepository {
        return FixerRepository(fixerApi)
    }
}