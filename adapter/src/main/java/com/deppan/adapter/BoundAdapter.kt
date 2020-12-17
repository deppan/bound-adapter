package com.deppan.adapter

import androidx.recyclerview.widget.DiffUtil

abstract class BoundAdapter<T>(callback: DiffUtil.ItemCallback<T>) :
    DiffAdapter<T, BoundViewHolder>(callback) {

    override fun onBindViewHolder(holder: BoundViewHolder, position: Int) {
        bindTo(holder, getItem(position))
        holder.ui.executePendingBindings()
    }

    override fun onBindViewHolder(
        holder: BoundViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) = bindTo(holder, getItem(position), payloads)

    open fun bindTo(holder: BoundViewHolder, item: T, payloads: MutableList<Any>) {}

    abstract fun bindTo(holder: BoundViewHolder, item: T)
}