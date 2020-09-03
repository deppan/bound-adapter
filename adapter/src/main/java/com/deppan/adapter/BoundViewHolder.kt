package com.deppan.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BoundViewHolder<VDB : ViewDataBinding>(view: View) : BaseViewHolder(view) {
    val binding = DataBindingUtil.bind<VDB>(view)!!
}