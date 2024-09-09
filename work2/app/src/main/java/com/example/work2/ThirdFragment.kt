package com.example.work2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.work2.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {
    private lateinit var binding: FragmentThirdBinding
    private var image_change = false
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

        binding.imageView.setOnClickListener {
            if(!image_change) {
                binding.imageView.setImageResource(R.drawable.varan3hat)
                image_change = true
            }
            else{
                binding.imageView.setImageResource(R.drawable.varan3)
                image_change = false
            }
        }
        binding.buttonFr3ToFr2.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.buttonFr3ToFr2API.setOnClickListener {

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