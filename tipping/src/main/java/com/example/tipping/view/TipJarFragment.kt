package com.example.tipping.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tipping.databinding.FragmentTipjarBinding
import com.example.tipping.viewmodel.TipJarViewModel
import com.example.tipping.viewmodel.TipJarViewModelImpl
import org.koin.androidx.viewmodel.ext.android.viewModel

class TipJarFragment : Fragment() {

    private lateinit var binding: FragmentTipjarBinding
    private val viewModel: TipJarViewModel by viewModel<TipJarViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentTipjarBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }
}
