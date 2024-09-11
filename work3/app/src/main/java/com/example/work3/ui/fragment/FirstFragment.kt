package com.example.work3.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.work3.viewmodel.FirstFragmentViewModel
import com.example.work3.R
import com.example.work3.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {
    private lateinit var viewModel: FirstFragmentViewModel
    private lateinit var binding: FragmentFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Создание экземпляра класса Fragment1Binding и связывание его с разметкой фрагмента
        binding = FragmentFirstBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(FirstFragmentViewModel::class.java)


        binding.imageView.setOnClickListener {
            viewModel.toggleImage()
        }
        viewModel._currentImage.observe(viewLifecycleOwner,
            Observer {data ->
            binding.imageView.setImageResource(data)

        })

        binding.buttonFr1ToFr2API.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FirstFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}