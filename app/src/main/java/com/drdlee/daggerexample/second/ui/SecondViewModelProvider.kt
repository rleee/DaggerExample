package com.drdlee.daggerexample.second.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class SecondViewModelProvider
@Inject constructor(
    private val viewModelProvider: Provider<SecondViewModel>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelProvider.get() as T
    }
}