package com.drdlee.daggerexample.third.repository

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class ThirdRepository
@Inject constructor() {

    private val detailText = MutableLiveData<String>("Third details from repo")

    fun getText() = detailText.value
}