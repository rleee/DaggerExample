package com.drdlee.daggerexample.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drdlee.daggerexample.databinding.FragmentFirstBinding
import com.drdlee.daggerexample.databinding.FragmentSecondBinding
import java.lang.IllegalStateException

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSecondBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }
}