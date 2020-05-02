package com.drdlee.daggerexample.second.repository

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class Repository
@Inject constructor() { // <-- Constructor injection, will mark this as object to provide to other constructor

    private val username = MutableLiveData<String>("Value from Repository")
    fun fetchUsername() = username.value
}