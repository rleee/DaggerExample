package com.drdlee.daggerexample.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.drdlee.daggerexample.databinding.FragmentFirstBinding
import com.drdlee.daggerexample.databinding.FragmentSecondBinding
import com.drdlee.daggerexample.second.di.DaggerSecondFactoryComponent
import com.drdlee.daggerexample.second.ui.SecondViewModel
import com.drdlee.daggerexample.second.ui.SecondViewModelProvider
import java.lang.IllegalStateException
import javax.inject.Inject

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    @Inject // <-- Property injection
    lateinit var factory: SecondViewModelProvider
    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        DaggerSecondFactoryComponent.create().injectTo(this) // <-- Injecting to this Fragment
        binding = FragmentSecondBinding.inflate(inflater)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this, factory).get(SecondViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.secondSessionText.text = viewModel.username
    }
}