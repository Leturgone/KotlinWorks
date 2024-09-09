package com.example.work2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.work2.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Создание экземпляра класса Fragment1Binding и связывание его с разметкой фрагмента
        binding = FragmentFirstBinding.inflate(inflater, container, false)


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