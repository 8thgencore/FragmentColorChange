package com.example.fragmentcolorchange

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_color_list.view.*


class ColorListFragment : Fragment() {
    private val colorList =
        listOf("Красный", "Оранжевый", "Желтый", "Зеленый", "Голубой", "Синий", "Фиолетовый")

    lateinit var viewModel: MainViewModel

        // Интерфейс для клика
    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }
    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object :
            RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view.setOnClickListener(null)
            }
            override fun onChildViewAttachedToWindow(view: View) {
                view.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_color_list, container, false)

        // Adapter
        val adapter = ColorAdapter(view.context, colorList)
        fun toggleSelection(position: Int) {
            adapter.toggleSelection(position)
        }

        view.color_list.adapter = adapter
        view.color_list.layoutManager = LinearLayoutManager(view.context)
        view.color_list.addItemDecoration(
            DividerItemDecoration(
                view.context,
                LinearLayoutManager.HORIZONTAL
            )
        )
        view.color_list.addItemDecoration(
            DividerItemDecoration(
                view.context,
                LinearLayoutManager.VERTICAL
            )
        )

        view.color_list.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                viewModel = ViewModelProvider(
                    requireActivity(),
                    ViewModelProvider.NewInstanceFactory()
                ).get(MainViewModel::class.java)

                viewModel.position.value = position
                toggleSelection(position)

                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    activity!!.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, ColorDetailsFragment())
                        .addToBackStack(null)
                        .commit()
                } else {
                    activity!!.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_container, ColorDetailsFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
        })
        return view
    }
}