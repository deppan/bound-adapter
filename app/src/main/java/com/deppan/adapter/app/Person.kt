package com.deppan.adapter.app

import androidx.recyclerview.widget.DiffUtil

data class Person(val id: Int, val name: String) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
                oldItem.id == newItem.id && oldItem.name == newItem.name

            override fun getChangePayload(oldItem: Person, newItem: Person): Any? {
                return super.getChangePayload(oldItem, newItem)
            }
        }
    }
}