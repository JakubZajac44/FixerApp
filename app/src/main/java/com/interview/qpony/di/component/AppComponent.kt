package com.interview.qpony.di.component

import android.app.Application
import com.interview.qpony.QponyApp
import com.interview.qpony.di.module.*
import dagger.BindsInstance
import dagger.Component

import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,  ActivityBuildersModule::class, NetworkModule::class, ViewModelModule::class, RepositoryModule::class, UtilsModule::class]
)
interface AppComponent : AndroidInjector<QponyApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}