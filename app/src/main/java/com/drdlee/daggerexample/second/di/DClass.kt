package com.drdlee.daggerexample.second.di

import androidx.lifecycle.ViewModelProvider
import com.drdlee.daggerexample.first.SecondFragment
import com.drdlee.daggerexample.second.ui.SecondViewModelProvider
import dagger.Binds
import dagger.Component
import dagger.Module

/**
 * Component to create ViewModelFactory from module
 * and inject to SecondFragment (property injection)
 *
 * the flow here would be 2 injection:
 *
 * 1) Property injection
 *    factory <-- Fragment <-- SecondFactoryComponent <-- SecondFactoryModule <-- SecondViewModelProvider
 *
 * 2) Constructor injection
 *    viewModel <-- Repository
 *
 */
@Component(modules = [SecondFactoryModule::class])
interface SecondFactoryComponent {
    fun injectTo(fragment: SecondFragment)
}

/**
 * Two kind of Module
 *
 * - Class and Provides (first example)
 *   --> why? because if we need to configure the 3rdParty object
 *
 * - Abstract Class and Binds (this example)
 *   --> directly create the 3rdParty object without we manually return it
 */
@Module
abstract class SecondFactoryModule {
    @Binds
    abstract fun bindFactory(factory: SecondViewModelProvider): ViewModelProvider.Factory
}