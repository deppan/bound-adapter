package com.deppan.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil

abstract class BoundAdapter<T, VDB : ViewDataBinding>(callback: DiffUtil.ItemCallback<T>) :
    BaseAdapter<T, BoundViewHolder<VDB>>(callback) {

    override fun onBindViewHolder(holder: BoundViewHolder<VDB>, position: Int) {
        bindTo(holder, getItem(position))
        holder.binding.executePendingBindings()
    }

    override fun onBindViewHolder(
        holder: BoundViewHolder<VDB>,
        position: Int,
        payloads: MutableList<Any>
    ) = bindTo(holder, getItem(position), payloads)

    open fun bindTo(holder: BoundViewHolder<VDB>, item: T, payloads: MutableList<Any>) {}

    abstract fun bindTo(holder: BoundViewHolder<VDB>, item: T)
}