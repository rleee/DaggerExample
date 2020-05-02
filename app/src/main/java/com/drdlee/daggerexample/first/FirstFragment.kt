package com.drdlee.daggerexample.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drdlee.daggerexample.databinding.FragmentFirstBinding
import javax.inject.Inject

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    @Inject
    lateinit var repo: Repo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        DaggerRepoComponent.create().injectTo(this)
        binding = FragmentFirstBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.firstSessionText.text = repo.getText
    }
}