package com.drdlee.daggerexample.third.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.drdlee.daggerexample.third.ui.ThirdViewModelProvider
import com.drdlee.daggerexample.third.ui.thirdfragment.ThirdFragment
import com.drdlee.daggerexample.third.ui.thirdfragment.ThirdFragmentViewModel
import com.drdlee.daggerexample.third.ui.thirdfragmentdetails.ThirdDetailsFragment
import com.drdlee.daggerexample.third.ui.thirdfragmentdetails.ThirdFragmentDetailsViewModel
import dagger.Binds
import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Component(modules = [ViewModelModule::class])
interface ThirdFragment {
    fun injectTo(thirdFragment: ThirdFragment)
    fun injectTo(thirdDetails: ThirdDetailsFragment)
}

@Module
internal abstract class ViewModelModule {
    @Binds
    abstract fun bindThirdFactory(thirdFactory: ThirdViewModelProvider): ViewModelProvider.Factory

    @Binds
    @IntoMap // <-- Making it into a Map
    @ViewModelKey(ThirdFragmentViewModel::class) // <-- The MapKey and the value will be the instance of @Binds
    abstract fun bindThirdViewModel(Third: ThirdFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ThirdFragmentDetailsViewModel::class)
    abstract fun bindThirdDetailsViewModule(ThirdDetails: ThirdFragmentDetailsViewModel): ViewModel
}

/**
 * Making the MapKey to map the ViewModels
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)