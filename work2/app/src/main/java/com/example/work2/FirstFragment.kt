package com.example.work2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.work2.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private var image_change = false

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


        binding.imageView.setOnClickListener {
            if(!image_change) {
                binding.imageView.setImageResource(R.drawable.varan1hat)
                image_change = true
            }
            else{
                binding.imageView.setImageResource(R.drawable.varan1)
                image_change = false
            }
        }


        binding.buttonFr1ToFr2.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(
                R.id.nav_host_fragment,SecondFragment()).addToBackStack(null).commit()
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
