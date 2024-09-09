package com.example.work2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.work2.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
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
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false)

        binding.imageView.setOnClickListener {
            if(!image_change) {
                binding.imageView.setImageResource(R.drawable.varan2hat)
                image_change = true
            }
            else{
                binding.imageView.setImageResource(R.drawable.varan2)
                image_change = false
            }
        }
        binding.buttonFr2ToFr1.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.buttonFr2ToFr1API.setOnClickListener {

        }
        binding.buttonFr2ToFr3.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(
                R.id.nav_host_fragment,ThirdFragment()).addToBackStack(null).commit()
        }
        binding.buttonFr2ToFr3API.setOnClickListener {

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