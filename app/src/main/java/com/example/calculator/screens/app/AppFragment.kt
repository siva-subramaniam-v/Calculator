package com.example.calculator.screens.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.R
import com.example.calculator.databinding.FragmentAppBinding

class AppFragment : Fragment() {

    private lateinit var binding : FragmentAppBinding
    private lateinit var viewModel : AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_app, container, false
        )

        viewModel = ViewModelProvider(this)[AppViewModel::class.java]

        binding.appViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}