package com.example.work3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.work3.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var viewModel: SecondFragmentViewModel
    private lateinit var binding: FragmentSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSecondBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SecondFragmentViewModel::class.java)

        binding.imageView.setOnClickListener {
            viewModel.toggleImage()
        }
        viewModel._currentImage.observe(viewLifecycleOwner,
            Observer {data ->
                binding.imageView.setImageResource(data)

            })

        binding.buttonFr2ToFr1API.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }

        binding.buttonFr2ToFr3API.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }

        return binding.root
    }

    companion object {

        fun newInstance() =
            SecondFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}