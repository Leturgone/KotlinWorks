package com.example.work3.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.work3.R
import com.example.work3.viewmodel.ThirdFragmentViewModel
import com.example.work3.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    private  lateinit var viewModel: ThirdFragmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(this).get(ThirdFragmentViewModel::class.java)

        binding.imageView.setOnClickListener {
            viewModel.toggleImage()
        }

        viewModel._currentImage.observe(viewLifecycleOwner, Observer { data ->
            binding.imageView.setImageResource(data)

        })
        binding.buttonFr3ToFr2API.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ThirdFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}