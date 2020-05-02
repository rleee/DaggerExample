package com.drdlee.daggerexample.second.ui

import androidx.lifecycle.ViewModel
import com.drdlee.daggerexample.second.repository.Repository
import javax.inject.Inject

class SecondViewModel
@Inject constructor( // <-- Constructor injection, will inject Repository here
    private val repository: Repository
) : ViewModel() {

    val username = repository.fetchUsername()
}