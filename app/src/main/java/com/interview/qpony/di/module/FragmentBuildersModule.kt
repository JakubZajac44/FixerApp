package com.interview.qpony.di.module

import com.interview.qpony.ui.CurrencyDetailFragment
import com.interview.qpony.ui.CurrencyHomeFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCurrencyHomeFragment(): CurrencyHomeFragment

    @ContributesAndroidInjector
    abstract fun contributeCurrencyDetailFragment(): CurrencyDetailFragment

}