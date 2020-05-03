package com.drdlee.daggerexample.second.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class SecondViewModelProvider
@Inject constructor(
    private val viewModelProvider: Provider<SecondViewModel> // <-- without Dagger, when we wants to -
                                                             // provide Repository to viewModel through constructor:
    // private val repository: Repository <-- code
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelProvider.get() as T
    }

    // without Dagger, the overwrite will be written like:
    //
    // override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    // if (modelClass.isAssignableFrom(SecondViewModel::class.java)) {  <-- is able to assign from SecondViewModel
    //         return SecondViewModel(repository) as T                  <-- pass repository to ViewModel
    //     }
    //     throw IllegalArgumentException("Unknown ViewModel class")
    // }
}