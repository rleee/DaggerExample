package com.drdlee.daggerexample.third.ui.thirdfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ThirdFragmentViewModel
@Inject constructor() : ViewModel() {

    private val thirdFragmentText = MutableLiveData<String>("Third Fragment Main")

    fun getText() = thirdFragmentText.value
}