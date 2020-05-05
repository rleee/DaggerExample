package com.drdlee.daggerexample.third.ui.thirdfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.drdlee.daggerexample.databinding.FragmentThirdBinding
import com.drdlee.daggerexample.third.di.DaggerThirdFragment
import com.drdlee.daggerexample.third.ui.ThirdViewModelProvider
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

class ThirdFragment : Fragment() {

    @Inject
    lateinit var factory: ThirdViewModelProvider

    private lateinit var binding: FragmentThirdBinding
    private lateinit var viewModel: ThirdFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        DaggerThirdFragment.create().injectTo(this)
        viewModel = ViewModelProvider(this, factory).get(ThirdFragmentViewModel::class.java)

        binding = FragmentThirdBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.thirdButton.setOnClickListener {
            this.findNavController()
                .navigate(ThirdFragmentDirections.actionNavThirdToThirdDetailsFragment())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.thirdText.text = viewModel.getText()
    }
}