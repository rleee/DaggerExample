package com.drdlee.daggerexample.third.ui.thirdfragmentdetails

import androidx.lifecycle.ViewModel
import com.drdlee.daggerexample.third.repository.ThirdRepository
import javax.inject.Inject

class ThirdFragmentDetailsViewModel
@Inject constructor(val repository: ThirdRepository) : ViewModel() {

    fun getText() = repository.getText()
}