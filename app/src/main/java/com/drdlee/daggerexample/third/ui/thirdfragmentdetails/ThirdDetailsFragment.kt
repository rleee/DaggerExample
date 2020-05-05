package com.drdlee.daggerexample.third.ui.thirdfragmentdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.drdlee.daggerexample.databinding.FragmentThirdDetailsBinding
import com.drdlee.daggerexample.third.di.DaggerThirdFragment
import com.drdlee.daggerexample.third.ui.ThirdViewModelProvider
import javax.inject.Inject

class ThirdDetailsFragment : Fragment() {

    @Inject
    lateinit var factory: ThirdViewModelProvider

    private lateinit var binding: FragmentThirdDetailsBinding
    private lateinit var viewModel: ThirdFragmentDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        DaggerThirdFragment.create().injectTo(this)
        viewModel = ViewModelProvider(this, factory).get(ThirdFragmentDetailsViewModel::class.java)
        binding = FragmentThirdDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.thirdDetailsText.text = viewModel.getText()
    }
}