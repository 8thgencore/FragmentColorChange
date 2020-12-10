package com.example.fragmentcolorchange

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(context: Context, colorList: List<String>) :
    RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    private var items: List<String>? = colorList
    private var inflater: LayoutInflater = LayoutInflater.from(context)

    private val selection = mutableSetOf<Int>()
    // fun hasSelected() = selection.isNotEmpty()
    private fun isSelected(position: Int) = selection.contains(position)
    fun toggleSelection(position: Int)
    {
        // если элемент выделен то сделать не выделенным
        // если элемент не выделен, то сделать выделенным
//        if(isSelected(position))
//            selection.remove(position)
//        else
//            selection.add(position)
        selection.clear()
        selection.add(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = inflater.inflate(viewType, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val item = items?.get(position)
        holder.tvColor.text = item
    }

    override fun getItemViewType(position: Int): Int {
        return if (isSelected(position))
            R.layout.item_selected
        else
            R.layout.item
    }

    override fun getItemCount(): Int = items?.size ?: 0

    class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvColor: TextView = itemView.findViewById(R.id.tvColor)
    }

}