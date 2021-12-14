package com.interview.qpony.di.module

import com.interview.qpony.util.DateUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Singleton
    @Provides
    fun provideDateUtil(): DateUtils {
        return DateUtils()
    }
}