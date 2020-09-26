package com.deppan.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : BaseViewHolder> : RecyclerView.Adapter<VH>() {

    private val data = arrayListOf<T>()

    override fun onBindViewHolder(holder: VH, position: Int) {
        bindTo(holder, data[position], position)
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        bindTo(holder, data[position], payloads, position)
    }

    override fun getItemCount(): Int = data.size

    fun add(element: T) {
        data.add(element)
        notifyItemInserted(data.size - 1)
    }

    fun addAll(elements: Collection<T>) {
        val position = data.size
        data.addAll(elements)
        notifyItemRangeInserted(position, elements.size)
    }

    fun resetWith(elements: Collection<T>) {
        data.clear()
        data.addAll(elements)
        notifyDataSetChanged()
    }

    fun clear() {
        val size = data.size
        data.clear()
        notifyItemRangeRemoved(0, size)
    }

    open fun bindTo(holder: VH, item: T, payloads: MutableList<Any>, position: Int) {}

    abstract fun bindTo(holder: VH, item: T, position: Int)
}