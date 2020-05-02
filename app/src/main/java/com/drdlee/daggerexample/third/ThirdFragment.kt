package com.drdlee.daggerexample.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drdlee.daggerexample.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentThirdBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

    companion object {
        fun create(): ThirdFragment = ThirdFragment()
    }
}