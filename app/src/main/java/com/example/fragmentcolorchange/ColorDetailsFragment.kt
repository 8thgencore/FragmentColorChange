package com.example.fragmentcolorchange

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_color_details.view.*


class ColorDetailsFragment : Fragment() {

    private val COLOR_NAME_LIST =
        listOf("Красный", "Оранжевый", "Желтый", "Зеленый", "Голубой", "Синий", "Фиолетовый")

    private val COLOR_VALUE_LIST =
        listOf("#FF0000", "#FF8800", "#FFEB3B", "#4CAF50", "#00BCD4", "#2196F3", "#673AB7")

    // Create an instance of our ViewModel
    lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_color_details, container, false)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        var position = if (viewModel.position.value !is Int) {
            0
        } else {
            viewModel.position.value!!
        }

        viewModel.position.observe(requireActivity(), Observer { position = it })

        view.color_name.text = COLOR_NAME_LIST[position]
        view.color_image.setColorFilter(Color.parseColor(COLOR_VALUE_LIST[position]))

        return view
    }


}